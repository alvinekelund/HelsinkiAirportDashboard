package Data

import Data.Flight._
import sttp.client3._
import scala.xml.{Elem, XML}
import java.io.StringReader
import scalafx.collections.ObservableBuffer
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.ZoneId

object DataParser {

  var timeCalled = ""

  private var callTimes = 0

  //checking for accidental repeated recursion
  def apiCallCounter() =
    callTimes += 1
    if callTimes > 1000 then
      throw new Exception("Too many calls to API")

  // finds time from xml file, used for saved files.
  def getTimestamp(xmlData: String): String = 
    val xmlElem: Elem = XML.loadString(xmlData)
    val timestampStr = (xmlElem \\ "header" \ "timestamp").headOption.map(_.text).getOrElse("")
    if (timestampStr.nonEmpty) then
      var timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ISO_DATE_TIME)
      timestamp = timestamp.plusHours(3)
            val formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))
      formattedTimestamp
    else
      "Timestamp not found"

// Get time in utc and add three hours to adjust for the time difference to Finland, returns the formatted time.
  def getLiveTime(): String = 
      val currentTimeUTC = LocalDateTime.now(ZoneId.of("UTC"))
      val finlandTime = currentTimeUTC.plusHours(3)
      val formattedTime = finlandTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))
      formattedTime

// used when saving, this gets the whole xml file to a string that is written to a saved file.
  def xmlFlightData(): String = 
    apiCallCounter()
    var request =
      basicRequest
        .get(uri"https://api.finavia.fi/flights/public/v0/flights/all/all")
        .header("Accept", "application/xml")
        .header("app_id", "0f1e817b")
        .header("app_key", "8a61352ed66585e182e53a3107b4ab4c")
    var backend = HttpURLConnectionBackend()
    var response = request.send(backend)
    response.toString
    var xmlContent = response.body.getOrElse("")
    xmlContent

// separates departing and arriving flights for a loaded file
  def separateXmlData(xmlData: String): (String, String) = 
    val depStartIndex = xmlData.indexOf("<dep>")
    val depEndIndex = xmlData.indexOf("</dep>", depStartIndex)
    val arrStartIndex = xmlData.indexOf("<arr>")
    val arrEndIndex = xmlData.indexOf("</arr>", arrStartIndex)
    val departureXml = xmlData.substring(depStartIndex, depEndIndex + "</dep>".length)
    val arrivalXml = xmlData.substring(arrStartIndex, arrEndIndex + "</arr>".length)
    (departureXml, arrivalXml)
    
  // Processes loaded flight data to a ObservableBuffer.
  def getLoadedFlightData(loadedData: String, set: String): ObservableBuffer[Flight] = 
    timeCalled = getTimestamp(loadedData)
    val xmlReader = 
      if set == "all" then
        new StringReader(loadedData)
      else if set == "dep" then 
        new StringReader(separateXmlData(loadedData)._1)
      else
        new StringReader(separateXmlData(loadedData)._2)
    val xmlElem: Elem = XML.load(xmlReader)
    val flights: ObservableBuffer[Flight] = ObservableBuffer.empty[Flight] 
    (xmlElem \\ "flight").foreach(flightElem =>
      val flight = Flight.fromXml(flightElem)
        if (isFlightToOrFromHEL(flight)) then
          flights.add(flight))
    flights



  // Get live data
  def getFlightData(set: String): ObservableBuffer[Flight] = 
    apiCallCounter()
    val request =
      basicRequest
        .get(uri"https://api.finavia.fi/flights/public/v0/flights/$set/all")
        .header("Accept", "application/xml")
        .header("app_id", "0f1e817b")
        .header("app_key", "8a61352ed66585e182e53a3107b4ab4c")

    val backend = HttpURLConnectionBackend()
    val response = request.send(backend)
    response.toString
    val xmlContent = response.body.getOrElse("")
    timeCalled = getLiveTime()
    val xmlReader = new StringReader(xmlContent)
    val xmlElem: Elem = XML.load(xmlReader)

    val flights: ObservableBuffer[Flight] = ObservableBuffer.empty[Flight] 
    (xmlElem \\ "flight").foreach(flightElem =>
      val flight = Flight.fromXml(flightElem)
      if (isFlightToOrFromHEL(flight) && isFlightInTheNext24h(flight)) then
        flights.add(flight))
    flights

    // Dahboard only considers flights to or from Hel
  private def isFlightToOrFromHEL(flight: Flight): Boolean =
    flight.h_apt == "HEL" || flight.route_1 == "HEL"

    // to make time series make sense, the following 24 h are considered.
  private def isFlightInTheNext24h(flight: Flight): Boolean = 
    val sdtFormatter = DateTimeFormatter.ISO_DATE_TIME
    val flightSdt = LocalDateTime.parse(flight.sdt, sdtFormatter)
    val currentDateTime = LocalDateTime.now()
    val hoursUntilDeparture = ChronoUnit.HOURS.between(currentDateTime, flightSdt)
    hoursUntilDeparture <= 24

    //easily available methods for dashboard.
  def getAllFlightData(): ObservableBuffer[Flight] = 
    getFlightData("all")
  

  def getDepFlightData(): ObservableBuffer[Flight] = 
    getFlightData("dep")
  

  def getArrFlightData(): ObservableBuffer[Flight] = 
    getFlightData("arr")
  
  def getSepFlightData(): (ObservableBuffer[Flight], ObservableBuffer[Flight]) = 
    (getFlightData("dep"), getFlightData("arr"))
  

}
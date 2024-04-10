package Data

import Data.APIConnection._
import Data.Flight._

import sttp.client3._
import sttp.client3.circe._
import scala.xml.{Elem, XML}
import Data.DataParser.getFlightData
import java.io.StringReader
import scalafx.collections.ObservableBuffer
import scala.io.Source
import scala.collection.mutable.Map
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.ZoneId

object DataParser {

  var timeCalled = ""
  def getTimestamp(xmlData: String): String = {
    val xmlElem: Elem = XML.loadString(xmlData)
    val timestampStr = (xmlElem \\ "header" \ "timestamp").headOption.map(_.text).getOrElse("")
    
    if (timestampStr.nonEmpty) {
      // Parse the timestamp string into LocalDateTime
      var timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ISO_DATE_TIME)
      
      // Add three hours to the timestamp to account for the time difference to Finland
      timestamp = timestamp.plusHours(3)
      
      // Format the timestamp into desired format
      val formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))
      
      formattedTimestamp
    } else {
      "Timestamp not found"
    }
  }
  def getLiveTime(): String = {
      // Get the current time in UTC
      val currentTimeUTC = LocalDateTime.now(ZoneId.of("UTC"))
      
      // Add three hours to adjust for the time difference to Finland
      val finlandTime = currentTimeUTC.plusHours(3)
      
      // Format the time into the desired format
      val formattedTime = finlandTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))
      
      formattedTime
  }

  
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

  def separateXmlData(xmlData: String): (String, String) = {
  val depStartIndex = xmlData.indexOf("<dep>")
  val depEndIndex = xmlData.indexOf("</dep>", depStartIndex)
  val arrStartIndex = xmlData.indexOf("<arr>")
  val arrEndIndex = xmlData.indexOf("</arr>", arrStartIndex)

  if (depStartIndex != -1 && depEndIndex != -1 && arrStartIndex != -1 && arrEndIndex != -1) {
    val departureXml = xmlData.substring(depStartIndex, depEndIndex + "</dep>".length)
    val arrivalXml = xmlData.substring(arrStartIndex, arrEndIndex + "</arr>".length)
    (departureXml, arrivalXml)
  } else {
    throw new IllegalArgumentException("Invalid XML format: Departure and/or arrival data not found")
  }
}


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
    (xmlElem \\ "flight").foreach { flightElem =>
      val flight = Flight.fromXml(flightElem)
        if (isFlightToOrFromHEL(flight)) {
          flights.add(flight)
        
      }
    }
    flights



  
  def getFlightData(set: String): ObservableBuffer[Flight] = {
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
    (xmlElem \\ "flight").foreach { flightElem =>
      val flight = Flight.fromXml(flightElem)
      if (isFlightToOrFromHEL(flight) && isFlightInTheNext24h(flight)) {
        flights.add(flight)
      
    }
  }
    flights
  }
  def isFlightToOrFromHEL(flight: Flight): Boolean = {
    flight.h_apt == "HEL" || flight.route_1 == "HEL"
  }

  def isFlightInTheNext24h(flight: Flight): Boolean = {
    val sdtFormatter = DateTimeFormatter.ISO_DATE_TIME
    val flightSdt = LocalDateTime.parse(flight.sdt, sdtFormatter)
    val currentDateTime = LocalDateTime.now()
    val hoursUntilDeparture = ChronoUnit.HOURS.between(currentDateTime, flightSdt)

    hoursUntilDeparture <= 24
    
  }
  

  def getAllFlightData(): ObservableBuffer[Flight] = {
    getFlightData("all")
  }

  def getDepFlightData(): ObservableBuffer[Flight] = {
    getFlightData("dep")
  }

  def getArrFlightData(): ObservableBuffer[Flight] = {
    getFlightData("arr")
  }
  def getSepFlightData(): (ObservableBuffer[Flight], ObservableBuffer[Flight]) = {
    (getFlightData("dep"), getFlightData("arr"))
  }

}
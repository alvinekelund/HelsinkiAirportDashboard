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

object DataParser {


  def xmlFlightData(): String = 
    apiCallCounter()
    var result = ""
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
    result += "xxxxx\n" + xmlContent.toString
    request =
      basicRequest
        .get(uri"https://api.finavia.fi/flights/public/v0/flights/dep/all")
        .header("Accept", "application/xml")
        .header("app_id", "0f1e817b")
        .header("app_key", "8a61352ed66585e182e53a3107b4ab4c")

    backend = HttpURLConnectionBackend()
    response = request.send(backend)
    xmlContent = response.body.getOrElse("")
    result += "xxxxx\nyyyyy\n" + xmlContent.toString

    request =
      basicRequest
        .get(uri"https://api.finavia.fi/flights/public/v0/flights/arr/all")
        .header("Accept", "application/xml")
        .header("app_id", "0f1e817b")
        .header("app_key", "8a61352ed66585e182e53a3107b4ab4c")

    backend = HttpURLConnectionBackend()
    response = request.send(backend)
    xmlContent = response.body.getOrElse("")
    result += "yyyyy\nzzzzz\n" + xmlContent.toString + "\nzzzzz"
    result


  def getLoadedFlightData(set: String, loadedData: String): ObservableBuffer[Flight] = 
    var matches: String = ""
    set match {
      case "all" => 
        val regex = """xxxxx\n(.*?)\nxxxxx""".r
        matches = regex.findAllMatchIn(loadedData).map(_.group(1)).mkString
      case "dep" => 
        val regex = """yyyyy\n(.*?)\nyyyyy""".r
        matches = regex.findAllMatchIn(loadedData).map(_.group(1)).mkString
      case "arr" => 
        val regex = """zzzzz\n(.*?)\nzzzzz""".r
        matches = regex.findAllMatchIn(loadedData).map(_.group(1)).mkString
      case _ => throw new IllegalArgumentException("Invalid set value")
    }
    val xmlReader = new StringReader(matches)
    val xmlElem: Elem = XML.load(xmlReader)
    val flights: ObservableBuffer[Flight] = ObservableBuffer.empty[Flight] 
    (xmlElem \\ "flight").foreach { flightElem =>
      val flight = Flight.fromXml(flightElem)
        if (isFlightToOrFromHEL(flight) && isFlightInTheNext24h(flight)) {
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
    

}
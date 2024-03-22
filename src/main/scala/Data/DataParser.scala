package finaviaAPI

import finaviaAPI.APIConnection._
import finaviaAPI.Flight._

import sttp.client3._
import sttp.client3.circe._
import scala.xml.{Elem, XML}
import finaviaAPI.DataParser.getFlightData
import java.io.StringReader
import scalafx.collections.ObservableBuffer
import scala.io.Source
import scala.collection.mutable.Map
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object DataParser {

  
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
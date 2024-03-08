package finaviaAPI

import finaviaAPI.APIConnection._
import finaviaAPI.Flight._

import sttp.client3._
import sttp.client3.circe._
import scala.xml.{Elem, XML}
import finaviaAPI.APIClient.getFlightData
import java.io.StringReader
import scalafx.collections.ObservableBuffer

object APIClient {
  def getFlightData(): ObservableBuffer[Flight] = {
    apiCallCounter()
    val request =
      basicRequest
        .get(uri"https://api.finavia.fi/flights/public/v0/flights/all/all")
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
    (xmlElem \\ "flight").foreach {flightElem =>
      val flight = Flight.fromXml(flightElem)
      flights.add(flight)
    }
    
    flights



    /*flights.foreach { flight =>
      println(s"Flight Number: ${flight.fltnr}")
      println(s"Departure Time: ${flight.sdt}")
      println(s"Date: ${flight.sdate}")
      println(s"Route: ${flight.route_1}")
      println(s"Route Spelled: ${flight.actype}")
      println(s"Home Airport: ${flight.h_apt}")
      println(s"Aircraft registration: ${flight.acreg}")
      println(s"Aircraft type: ${flight.actype}")
      println(s"Callsign: ${flight.callsign}")
      println(s"Bltarea: ${flight.bltarea}")
      println("-----") 
    }*/
  }
}

package finaviaAPI

import finaviaAPI.APIConnection._
import finaviaAPI.Flight._

import sttp.client3._
import sttp.client3.circe._
import scala.xml.{Elem, XML}
import finaviaAPI.APIClient.getFlightData
import java.io.StringReader
import scalafx.collections.ObservableBuffer
import scala.io.Source
import scala.collection.mutable.Map

object APIClient {
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
    (xmlElem \\ "flight").foreach {flightElem =>
      val flight = Flight.fromXml(flightElem)
      flights.add(flight)
    }

    flights
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
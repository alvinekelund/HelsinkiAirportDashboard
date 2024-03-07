package finaviaAPI

import finaviaAPI.APIConnection.*

//import io.circe.generic.auto._
//import io.circe.Json

import sttp.client3._
import sttp.client3.circe._
import scala.xml.{Elem, XML}
import finaviaAPI.APIClient.getFlightData
import java.io.StringReader

object APIClient {
  def getFlightData(): Unit = {
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
    val flights: Seq[Flight] = (xmlElem \\ "flight").map(Flight.fromXml)
    flight.foreach(println)
  
    }

   // val flightNumbers = (xmlElem \\ "route_1").map(_.text)
    // flightNumbers.foreach(println)


  }
}


end APIClient


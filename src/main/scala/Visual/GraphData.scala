package Visual

import scala.io.Source
import scala.collection.mutable.Map
import scalafx.collections.ObservableBuffer
import finaviaAPI.Flight
import java.time.format.DateTimeFormatter
import java.time.LocalTime
import java.time.LocalDateTime

class GraphData {

  private val callsignMap: Map[String, String] = loadCallsignMap()

  
  private def loadCallsignMap(): Map[String, String] = {
    val callsignMap = Map[String, String]()

    val callsignLines = Source.fromFile("callsigns.txt").getLines()
    val airlinesLines = Source.fromFile("airlines.txt").getLines()

    for ((callsign, airline) <- callsignLines.zip(airlinesLines)) {
      callsignMap += (callsign.trim -> airline.trim)
    }

    callsignMap
  }

  def flightsPerCarrierData(data: ObservableBuffer[Flight]): Array[(String, Int)] = {
  val carrierCountMap = Map[String, Int]().withDefaultValue(0)

  for (flight <- data) {
    val callsignPrefix = flight.callsign.take(3)
    if (callsignMap.contains(callsignPrefix)) {
      val carrier = callsignMap(callsignPrefix)
      carrierCountMap(carrier) += 1
    }
  }

  carrierCountMap.toArray.sortBy(-_._2)
}

  def flightPerHourData(data: ObservableBuffer[Flight]): Array[(String, Int)] = {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    // Group flights by hour
    val flightsByHour = data.groupBy(flight => LocalDateTime.parse(flight.sdt, formatter).getHour)

    // Map each group to (hour, number of flights) tuple
    flightsByHour.map { case (hour, flights) =>
      val formattedHour = f"$hour%02d:00" // Format hour as HH:00
      (formattedHour, flights.size)
    }.toArray.sortBy(_._1) // Sort by hour
  }
}

  


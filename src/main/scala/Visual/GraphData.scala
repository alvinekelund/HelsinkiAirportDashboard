package Visual

import scala.io.Source
import scala.collection.mutable.Map
import scalafx.collections.ObservableBuffer
import Data.Flight
import java.time.format.DateTimeFormatter
import java.time.{LocalTime, LocalDateTime, ZoneId}


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

  def flightPerCarrierData(data: ObservableBuffer[Flight]): Array[(String, Int)] = {
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

  // Group flights by hour adjusted for Finland time zone
  val flightsByHour = data.groupBy { flight =>
    val gmtTime = LocalDateTime.parse(flight.sdt, formatter)
    val finlandTime = gmtTime.atZone(ZoneId.of("GMT")).withZoneSameInstant(ZoneId.of("Europe/Helsinki")).toLocalDateTime()
    finlandTime.getHour
  }

  // Map each group to (hour, number of flights) tuple
  flightsByHour.map { case (hour, flights) =>
    val formattedHour = f"$hour%02d:00" // Format hour as HH:00
    (formattedHour, flights.size)
  }.toArray.sortBy(_._1) // Sort by hour
}




  def flightPerHourDepArr(depData: ObservableBuffer[Flight], arrData: ObservableBuffer[Flight]): Array[(String, Int, Int)] = {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

  // Define the time zone for Finland
  val finlandTimeZone = ZoneId.of("Europe/Helsinki")

  // Initialize map with all hours of the day and flight count for departures and arrivals set to 0
  var flightCounts = Map.from((0 until 24).map(hour => f"$hour%02d:00" -> (0, 0)))

  // Update flight counts for hours with flights
  depData.foreach { flight =>
    // Convert GMT time to Finland time zone
    val gmtTime = LocalDateTime.parse(flight.sdt, formatter)
    val finlandTime = gmtTime.atZone(ZoneId.of("GMT")).withZoneSameInstant(finlandTimeZone).toLocalDateTime()

    // Get the hour in Finland time zone
    val hour = finlandTime.getHour
    val formattedHour = f"$hour%02d:00"

    // Update departure flight count for the hour
    val (depCount, arrCount) = flightCounts(formattedHour)
    flightCounts += formattedHour -> (depCount + 1, arrCount)
  }

  arrData.foreach { flight =>
    // Convert GMT time to Finland time zone
    val gmtTime = LocalDateTime.parse(flight.sdt, formatter)
    val finlandTime = gmtTime.atZone(ZoneId.of("GMT")).withZoneSameInstant(finlandTimeZone).toLocalDateTime()

    // Get the hour in Finland time zone
    val hour = finlandTime.getHour
    val formattedHour = f"$hour%02d:00"

    // Update arrival flight count for the hour
    val (depCount, arrCount) = flightCounts(formattedHour)
    flightCounts += formattedHour -> (depCount, arrCount + 1)
  }

  // Convert map to sorted array of tuples
  val sortedArray = flightCounts.toArray.sortBy(_._1).map { case (hour, (depCount, arrCount)) => (hour, depCount, arrCount) }
  sortedArray
}
}

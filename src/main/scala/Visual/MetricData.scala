package Visual

import scala.io.Source
import scala.collection.mutable.Map
import scalafx.collections.ObservableBuffer
import Data.Flight
import java.time.format.DateTimeFormatter
import java.time.LocalTime
import java.time.LocalDateTime
import java.time.ZoneId

class MetricData {
  def totalFlights(data: ObservableBuffer[Flight]): String = {
    data.length.toString
  }

  def busiestHour(data: ObservableBuffer[Flight]): String = {
    // Initialize a map to store the count of flights for each hour
    val flightsByHour = scala.collection.mutable.Map[Int, Int]().withDefaultValue(0)

    // Define the formatter for parsing flight departure times
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("GMT"))

    // Iterate over each flight
    data.foreach { flight =>
      // Parse the departure time and extract the hour
      val departureTime = LocalDateTime.parse(flight.sdt, formatter)
      val hour = departureTime.plusHours(3).getHour // Add 3 hours to adjust for Finland time

      // Increment the count of flights for the corresponding hour
      flightsByHour(hour) += 1
    }

    // Find the hour with the maximum number of flights
    val (busiestHour, numFlights) = flightsByHour.maxBy(_._2)

    // Return the busiest hour and the number of flights during that hour as a single string
    f"$busiestHour%02d:00 ($numFlights)"
    
  }

  def leastBusyHour(data: ObservableBuffer[Flight]): String = {
   // Initialize a map to store the count of flights for each hour
    val flightsByHour = scala.collection.mutable.Map[Int, Int]().withDefaultValue(0)

    // Define the formatter for parsing flight departure times
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("GMT"))

    // Iterate over each flight
    data.foreach { flight =>
      // Parse the departure time and extract the hour
      val departureTime = LocalDateTime.parse(flight.sdt, formatter)
      val hour = departureTime.plusHours(3).getHour // Add 3 hours to adjust for Finland time

      // Increment the count of flights for the corresponding hour
      flightsByHour(hour) += 1
    }

    // Find the hour with the minimum number of flights
    val (leastBusyHour, numFlights) = flightsByHour.minBy(_._2)

    // Return the least busy hour and the number of flights during that hour as a single string
    f"$leastBusyHour%02d:00 ($numFlights)" 
  }
}
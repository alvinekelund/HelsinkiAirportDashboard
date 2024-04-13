package Data

import scala.collection.mutable.Map
import scalafx.collections.ObservableBuffer
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.ZoneId

class MetricData {
  val graphData = new GraphData

  def totalFlights(data: ObservableBuffer[Flight]): String = 
    data.length.toString
  
    // figures out the busiest hour
  def busiestHour(data: ObservableBuffer[Flight]): String = 
    val flightsByHour = scala.collection.mutable.Map[Int, Int]().withDefaultValue(0)
        // formatting to readable format
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("GMT"))
    // adjusts for time difference
    data.foreach(Flight =>
      val departureTime = LocalDateTime.parse(Flight.sdt, formatter)
      val hour = departureTime.plusHours(3).getHour 
      flightsByHour(hour) += 1
    )
    val (busiestHour, numFlights) = flightsByHour.maxBy(_._2)
    f"$busiestHour%02d:00 ($numFlights)"

    // figures out the least busy hour
  def leastBusyHour(data: ObservableBuffer[Flight]): String = 
    val flightsByHour = scala.collection.mutable.Map[Int, Int]().withDefaultValue(0)
    // formatting to readable format
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("GMT"))
        // adjusts for time difference
    data.foreach(flight =>
      val departureTime = LocalDateTime.parse(flight.sdt, formatter)
      val hour = departureTime.plusHours(3).getHour 
      flightsByHour(hour) += 1)
    val (leastBusyHour, numFlights) = flightsByHour.minBy(_._2)
    f"$leastBusyHour%02d:00 ($numFlights)" 
  
    // from the data takes most common carrier, uses callsignMap which is created in graphData
  def mostCommonCarrier(data: ObservableBuffer[Flight]): String = 
    val carrierCountMap = Map[String, Int]().withDefaultValue(0)
    for flight <- data do
      val callsignPrefix = flight.callsign.take(3)
      if (graphData.callsignMap.contains(callsignPrefix)) then 
        val carrier = graphData.callsignMap(callsignPrefix)
        carrierCountMap(carrier) += 1
    carrierCountMap.maxBy(_._2)._1
  
    // from the data takes most common model, uses planeIdMap which is created in graphData
  def mostCommonModel(data: ObservableBuffer[Flight]): String = 
    val modelCountMap = Map[String, Int]().withDefaultValue(0)
    for flight <- data do
      val id = flight.actype.take(3)
      if (graphData.planeIdMap.contains(id)) then
        val model = graphData.planeIdMap(id)
        modelCountMap(model) += 1
          // formats the string to fit
    val length = modelCountMap.maxBy(_._2)._1.length
    modelCountMap.maxBy(_._2)._1.splitAt(length/2)._1 + "\n" + modelCountMap.maxBy(_._2)._1.splitAt(length/2)._2
}
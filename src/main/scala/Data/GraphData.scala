package Data

import scala.io.Source
import scala.collection.mutable.Map
import scalafx.collections.ObservableBuffer
import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneId}


class GraphData {

  // maps for combining data from api call to some informative string
  val callsignMap: Map[String, String] = loadCallsignMap()
  val planeIdMap: Map[String, String] = loadPlaneIdMap()


  // creates the map from given txt files
  private def loadCallsignMap(): Map[String, String] =
    val callMap = Map[String, String]()
    val callsignLines = Source.fromFile("callsigns.txt").getLines()
    val airlinesLines = Source.fromFile("airlines.txt").getLines()
    for ((callsign, airline) <- callsignLines.zip(airlinesLines))(callMap += (callsign.trim -> airline.trim))
    callMap

      // creates the map from given txt files
  private def loadPlaneIdMap(): Map[String, String] = 
    val modelMap = Map[String, String]()
    val id = Source.fromFile("airplaneid.txt").getLines()
    val model = Source.fromFile("airplanemodel.txt").getLines()
    for ((id, model) <- id.zip(model))(modelMap += (id.trim -> model.trim))
    modelMap

    //Creates the data for the flight per carrier
  def flightPerCarrierData(data: ObservableBuffer[Flight]): Array[(String, Int)] = 
    val carrierCountMap = Map[String, Int]().withDefaultValue(0)
    for flight <- data do
      val callsignPrefix = flight.callsign.take(3)
      if (callsignMap.contains(callsignPrefix)) then
        val carrier = callsignMap(callsignPrefix)
        carrierCountMap(carrier) += 1
    carrierCountMap.toArray.sortBy(-_._2)

    //Creates the data for the flight per model
  def flightPerModelData(data: ObservableBuffer[Flight]): Array[(String, Int)] = 
    val modelCountMap = Map[String, Int]().withDefaultValue(0)
    for (flight <- data) do
      val id = flight.actype.take(3)
      if (planeIdMap.contains(id))then
        val model = planeIdMap(id)
        modelCountMap(model) += 1
    modelCountMap.toArray.sortBy(-_._2)

  // Creates the data for normal time series
  def flightPerHourData(data: ObservableBuffer[Flight]): Array[(String, Int)] = 
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val flightsByHour = data.groupBy(flight =>
      val gmtTime = LocalDateTime.parse(flight.sdt, formatter)
      val finlandTime = gmtTime.atZone(ZoneId.of("GMT")).withZoneSameInstant(ZoneId.of("Europe/Helsinki")).toLocalDateTime()
      finlandTime.getHour)
    flightsByHour.map{case (hour, flights) =>
      val formattedHour = f"$hour%02d:00"
      (formattedHour, flights.size)}
      .toArray.sortBy(_._1)

  // Creates the data for dual time series, to make it easier it take both series as parameters. Otherwise same as normal.
  def flightPerHourDepArr(depData: ObservableBuffer[Flight], arrData: ObservableBuffer[Flight]): Array[(String, Int, Int)] = 
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val finlandTimeZone = ZoneId.of("Europe/Helsinki")
    var flightCounts = Map.from((0 until 24).map(hour => f"$hour%02d:00" -> (0, 0)))
    depData.foreach(flight =>
      val gmtTime = LocalDateTime.parse(flight.sdt, formatter)
      val finlandTime = gmtTime.atZone(ZoneId.of("GMT")).withZoneSameInstant(finlandTimeZone).toLocalDateTime()
      val hour = finlandTime.getHour
      val formattedHour = f"$hour%02d:00"
      val (depCount, arrCount) = flightCounts(formattedHour)
      flightCounts += formattedHour -> (depCount + 1, arrCount))
    arrData.foreach(flight =>
      val gmtTime = LocalDateTime.parse(flight.sdt, formatter)
      val finlandTime = gmtTime.atZone(ZoneId.of("GMT")).withZoneSameInstant(finlandTimeZone).toLocalDateTime()
      val hour = finlandTime.getHour
      val formattedHour = f"$hour%02d:00"
      val (depCount, arrCount) = flightCounts(formattedHour)
      flightCounts += formattedHour -> (depCount, arrCount + 1))
    val sortedArray = flightCounts.toArray.sortBy(_._1).map {case (hour, (depCount, arrCount)) => (hour, depCount, arrCount)}
    sortedArray
}

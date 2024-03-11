package Visual

import scala.io.Source
import scala.collection.mutable.Map
import scalafx.collections.ObservableBuffer
import finaviaAPI.Flight

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

  def perCarrier(data: ObservableBuffer[Flight]): Array[(String, Int)] = {
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

}

  


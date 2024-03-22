package Visual

import scala.io.Source
import scala.collection.mutable.Map
import scalafx.collections.ObservableBuffer
import finaviaAPI.Flight
import java.time.format.DateTimeFormatter
import java.time.LocalTime
import java.time.LocalDateTime

class MetricData {
  def totalFlights(data: ObservableBuffer[Flight]): String = 
    data.length.toString
}

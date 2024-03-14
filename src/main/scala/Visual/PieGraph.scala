package Visual

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import finaviaAPI.DataParser.*
import scalafx.scene.control._
import scalafx.Includes._
import scalafx.event.ActionEvent
import java.awt.Graphics
import scalafx.stage.StageStyle.Unified
import javafx.stage.StageStyle
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty}
import finaviaAPI.Flight
import cats.instances.double
import javafx.scene.chart.{CategoryAxis, NumberAxis, XYChart, BarChart}
import scalafx.collections.ObservableBuffer
import scalafx.scene.chart.PieChart




class PieGraph {
  def createPieChart(dataSet: Array[(String, Int)], x: String, y: String, label: String): PieChart  = {
    val pieChartData = dataSet.map { case (label, value) => PieChart.Data(label, value) }
    val buffer = ObservableBuffer.from(pieChartData)

    
    val pieChart = new PieChart {
      title = label
      data = buffer
    }
    
    pieChart
  }
}
package Visual

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import Data.DataParser.*
import scalafx.scene.control._
import scalafx.Includes._
import scalafx.event.ActionEvent
import java.awt.Graphics
import scalafx.stage.StageStyle.Unified
import javafx.stage.StageStyle
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty}
import Data.Flight
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
    
    val b = pieChart
    val total = b.getData.foldLeft(0.0) {(x, y) => x + y.getPieValue}
      b.getData.foreach( d => {
        val sliceNode: scalafx.scene.Node = d.getNode
        val pieValue = d.getPieValue
        val percent = (pieValue / total) * 100
        val msg = "%s: %.0f (%.2f%%)".format(d.getName, pieValue, percent)
        val tt = new Tooltip()
        tt.setText(msg)
        tt.setStyle("-fx-background-color: lightgrey; " +  "-fx-text-fill: black; ")
        Tooltip.install(sliceNode, tt) })
    b
  }
}
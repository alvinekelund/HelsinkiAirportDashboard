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
import javafx.scene.layout.VBox
import java.awt.Graphics
import scalafx.stage.StageStyle.Unified
import javafx.stage.StageStyle
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty}
import finaviaAPI.Flight
import cats.instances.double
import javafx.scene.chart.{CategoryAxis, NumberAxis, XYChart, BarChart, ScatterChart, LineChart}


class ScatterPlot {
    def createScatterChart(dataSet: Array[(String, Int)], x: String, y: String, label: String): ScatterChart[String, Number]  = 
        val graphData = new GraphData()
        val xAxis = new CategoryAxis()
        val yAxis = new NumberAxis()
        xAxis.label = x
        yAxis.label = y
        val series = new XYChart.Series[String, Number]()
        series.setName(label)
        series.data = ObservableBuffer(dataSet.map(cca => XYChart.Data[String, Number](cca._1, cca._2)): _*)
        new ScatterChart[String, Number](xAxis, yAxis, ObservableBuffer(series))

   
    end createScatterChart
}

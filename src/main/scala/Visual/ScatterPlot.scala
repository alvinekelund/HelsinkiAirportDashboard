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
import javafx.scene.layout.VBox
import java.awt.Graphics
import scalafx.stage.StageStyle.Unified
import javafx.stage.StageStyle
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty}
import Data.Flight
import cats.instances.double
import javafx.scene.chart.{CategoryAxis, NumberAxis, XYChart, BarChart, ScatterChart, LineChart}
import scalafx.scene.Node
import scalafx.scene.control.*


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
        series.getData.foreach( d => {
        val pointNode: scalafx.scene.Node = d.getNode
        val pointValue = d.getYValue.toString
        val pointTime = d.getXValue.toString
        val roundedValue = BigDecimal(pointValue).setScale(1, BigDecimal.RoundingMode.HALF_UP)
        val tooltip = new Tooltip()
        tooltip.setText(pointTime + ": " + "$" + roundedValue.toString)
        tooltip.setStyle("-fx-background-color: yellow; " + "-fx-text-fill: black; ")
        Tooltip.install(pointNode, tooltip)
        })
        val b= new ScatterChart[String, Number](xAxis, yAxis, ObservableBuffer(series))

        b.getData.foreach { series =>
            series.getData.foreach { d =>
                val pointNode: scalafx.scene.Node = d.getNode
                val pointValue = d.getYValue.toString
                val pointTime = d.getXValue.toString
                val roundedValue = BigDecimal(pointValue).setScale(1, BigDecimal.RoundingMode.HALF_UP)
                val tooltip = new Tooltip()
                tooltip.setText(pointTime  + "\n"  + roundedValue.toString)
                tooltip.setStyle("-fx-background-color: lightgrey; " + "-fx-text-fill: black; ")
                Tooltip.install(pointNode, tooltip)
            }
            }
        b

      

   
    end createScatterChart
}

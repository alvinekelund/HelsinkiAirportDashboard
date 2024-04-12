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
import javafx.scene.chart.{CategoryAxis, NumberAxis, XYChart, BarChart, LineChart}
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent
import scalafx.stage.Stage
import scalafx.scene.layout.BorderPane
import scalafx.stage.Modality


class LinePlot {

    def createLineChart(dataSet: Array[(String, Int)], x: String, y: String, label: String, color: String): LineChart[String, Number]  = {
        val xAxis = new CategoryAxis()
        val yAxis = new NumberAxis()
        xAxis.label = x
        yAxis.label = y
    
        val series = new XYChart.Series[String, Number]()
        series.setName(label)

        series.data = ObservableBuffer(dataSet.map(cca => XYChart.Data[String, Number](cca._1, cca._2)): _*)
        val chart = new LineChart[String, Number](xAxis, yAxis, ObservableBuffer(series))
        series.getData.forEach { data =>
            val node = data.getNode
            node.setStyle("-fx-background-color: " + color.toString + "; ")
        }

        chart.getData.foreach { series =>
            series.getData.foreach { d =>
                val pointNode: scalafx.scene.Node = d.getNode
                val pointValueInt = d.getYValue.toString.toInt // Convert to integer
                val pointValue = pointValueInt.toString // Convert back to string for tooltip display
                val pointTime = d.getXValue.toString
                val tooltip = new Tooltip()
                tooltip.setText(pointTime  + "\n"  + pointValue)
                tooltip.setStyle("-fx-background-color: lightgrey; " + "-fx-text-fill: black; ")
                Tooltip.install(pointNode, tooltip)
                pointNode.setOnMouseClicked((event: MouseEvent) => {
                val dialog: Stage = new Stage {
                    initModality(Modality.ApplicationModal)
                    title = "Data Point Info"
                    scene = new Scene {
                    content = new BorderPane {
                        center = new Label(s"$pointTime\n$pointValue")
                    }
                    }
                }
                dialog.showAndWait()
                })
            }
            }
            chart
        }


    def createLineChartTwoSeries(dataSet: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String): LineChart[String, Number]  = 
        val xAxis = new CategoryAxis()
        val yAxis = new NumberAxis()
        xAxis.label = x
        yAxis.label = y
        
        val series1 = new XYChart.Series[String, Number]()
        series1.setName(label1)
        series1.data = ObservableBuffer(dataSet.map(data => XYChart.Data[String, Number](data._1, data._2)): _*)
        
        val series2 = new XYChart.Series[String, Number]()
        series2.setName(label2)
        series2.data = ObservableBuffer(dataSet.map(data => XYChart.Data[String, Number](data._1, data._3)): _*)
        
        val b = new LineChart[String, Number](xAxis, yAxis, ObservableBuffer(series1, series2))

        b.getData.foreach { series =>
        series.getData.foreach { d =>
            val pointNode: scalafx.scene.Node = d.getNode
            val pointTime = d.getXValue.toString
            val depCount = d.getYValue.toString.toInt // Departure count

            // Find the corresponding arrival count for the same time
            val arrCount = series match {
                case `series1` => dataSet.find(_._1 == pointTime).map(_._3).getOrElse(0)
                case `series2` => dataSet.find(_._1 == pointTime).map(_._2).getOrElse(0)
                case _ => 0
            }
            val tooltip = new Tooltip()
            tooltip.setText(s"$pointTime\nDepartures: $depCount\nArrivals: $arrCount")
            tooltip.setStyle("-fx-background-color: lightgrey; " + "-fx-text-fill: black; ")
            Tooltip.install(pointNode, tooltip)
            pointNode.setOnMouseClicked((event: MouseEvent) => {
                val dialog: Stage = new Stage {
                    initModality(Modality.ApplicationModal)
                    title = "Data Point Info"
                    scene = new Scene {
                    content = new BorderPane {
                        center = new Label(s"$pointTime\nDepartures: $depCount\nArrivals: $arrCount")
                    }
                    }
                }
                dialog.showAndWait()
            })
        }
        }
        b
    end createLineChartTwoSeries

}

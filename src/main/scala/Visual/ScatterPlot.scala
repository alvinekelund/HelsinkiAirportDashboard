package Visual

import scalafx.scene.Scene
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import javafx.scene.chart.{CategoryAxis, NumberAxis, XYChart, ScatterChart, LineChart}
import scalafx.scene.Node
import scalafx.scene.control.*
import javafx.scene.input.MouseEvent
import scalafx.scene.layout.BorderPane
import scalafx.stage.Stage
import scalafx.stage.Modality
import Data.GraphData


class ScatterPlot {
    def createScatterChart(dataSet: Array[(String, Int)], x: String, y: String, label: String, color: String): ScatterChart[String, Number]  = 
        val graphData = new GraphData()
        val xAxis = new CategoryAxis()
        val yAxis = new NumberAxis()
        xAxis.label = x
        yAxis.label = y
        val series = new XYChart.Series[String, Number]()
        series.setName(label)
        series.data = ObservableBuffer(dataSet.map(cca => XYChart.Data[String, Number](cca._1, cca._2)): _*)
        val chart = new ScatterChart[String, Number](xAxis, yAxis, ObservableBuffer(series))
        // color selection
        series.getData.forEach(data =>
            val node = data.getNode 
            node.setStyle("-fx-background-color: " + color.toString + "; "))
// tooltip logix
        chart.getData.foreach { series =>
            series.getData.foreach { d =>
                val pointNode: scalafx.scene.Node = d.getNode
                val pointValueInt = d.getYValue.toString.toInt
                val pointValue = pointValueInt.toString
                val pointTime = d.getXValue.toString
                val tooltip = new Tooltip()
                tooltip.setText(pointTime  + "\n"  + pointValue)
                tooltip.setStyle("-fx-background-color: lightgrey; " + "-fx-text-fill: black; ")
                Tooltip.install(pointNode, tooltip)
                // pop up box
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

    def createScatterChartTwoSeries(dataSet: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String): ScatterChart[String, Number]  = 
        val xAxis = new CategoryAxis()
        val yAxis = new NumberAxis()
        xAxis.label = x
        yAxis.label = y
        // Creating two separate series.
        val series1 = new XYChart.Series[String, Number]()
        series1.setName(label1)
        series1.data = ObservableBuffer(dataSet.map(data => XYChart.Data[String, Number](data._1, data._2)): _*)
        
        val series2 = new XYChart.Series[String, Number]()
        series2.setName(label2)
        series2.data = ObservableBuffer(dataSet.map(data => XYChart.Data[String, Number](data._1, data._3)): _*)
        // creating the chart
        val chart = new ScatterChart[String, Number](xAxis, yAxis, ObservableBuffer(series1, series2))
        // create the tooltop
        chart.getData.foreach { series =>
        series.getData.foreach { d =>
            val pointNode: scalafx.scene.Node = d.getNode
            val pointTime = d.getXValue.toString
            val depCount = d.getYValue.toString.toInt 

            val arrCount = series match {
                case `series1` => dataSet.find(_._1 == pointTime).map(_._3).getOrElse(0)
                case `series2` => dataSet.find(_._1 == pointTime).map(_._2).getOrElse(0)
                case _ => 0
            }
            val tooltip = new Tooltip()
            tooltip.setText(s"$pointTime\nDepartures: $depCount\nArrivals: $arrCount")
            tooltip.setStyle("-fx-background-color: lightgrey; " + "-fx-text-fill: black; ")
            Tooltip.install(pointNode, tooltip)
             //create the pop up box
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
        chart
    end createScatterChartTwoSeries
      

}

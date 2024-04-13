package Visual

import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import javafx.scene.chart.{CategoryAxis, NumberAxis, XYChart, BarChart}
import javafx.scene.input.MouseEvent
import scalafx.stage.Stage
import scalafx.stage.Modality
import scalafx.scene.layout.BorderPane



class ColumnChart {

    def createColumnChart(dataSet: Array[(String, Int)], x: String, y: String, label: String, color: String): BarChart[String, Number]  = 
        val xAxis = new CategoryAxis()
        val yAxis = new NumberAxis()
        xAxis.label = x
        yAxis.label = y
        val series = new XYChart.Series[String, Number]
        series.setName(label)
        series.data = dataSet.map(cca => XYChart.Data[String, Number](cca._1, cca._2))
        // creates the chart
        val chart= new BarChart[String, Number](xAxis, yAxis, ObservableBuffer(series)) 
        chart.getData.forEach(series =>
            // chart color
            series.getData.forEach(data =>
                val node = data.getNode 
                node.setStyle("-fx-bar-fill: " + color.toString + "; ")))
        
        // tooltip logic     
        chart.getData.foreach(series =>
            series.getData.foreach(d =>
                val pointNode: scalafx.scene.Node = d.getNode
                val pointValueInt = d.getYValue.toString.toInt 
                val pointValue = pointValueInt.toString
                val pointTime = d.getXValue.toString
                val tooltip = new Tooltip()
                tooltip.setText(pointTime  + "\n"  + pointValue)
                tooltip.setStyle("-fx-background-color: lightgrey; " + "-fx-text-fill: black; ")
                Tooltip.install(pointNode, tooltip)
                // popup box
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
                })))
        chart

    def createColumnChartTwoSeries(dataSet: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String): BarChart[String, Number]  = 
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
        //creates chart
        val chart = new BarChart[String, Number](xAxis, yAxis, ObservableBuffer(series1, series2))
        //tooltip logix
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
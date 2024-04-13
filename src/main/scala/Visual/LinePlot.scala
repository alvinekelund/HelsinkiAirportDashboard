package Visual

import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import javafx.scene.chart.{CategoryAxis, NumberAxis, XYChart, LineChart}
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
    //create the series
        val series = new XYChart.Series[String, Number]()
        series.setName(label)

        series.data = ObservableBuffer(dataSet.map(cca => XYChart.Data[String, Number](cca._1, cca._2)): _*)
        val chart = new LineChart[String, Number](xAxis, yAxis, ObservableBuffer(series))
        // set color 
        series.getData.forEach { data =>
            val node = data.getNode
            node.setStyle("-fx-background-color: " + color.toString + "; ")
        }
// tooltip logic
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
                //popup box
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
        //create two separeate series
        val series1 = new XYChart.Series[String, Number]()
        series1.setName(label1)
        series1.data = ObservableBuffer(dataSet.map(data => XYChart.Data[String, Number](data._1, data._2)): _*)
        
        val series2 = new XYChart.Series[String, Number]()
        series2.setName(label2)
        series2.data = ObservableBuffer(dataSet.map(data => XYChart.Data[String, Number](data._1, data._3)): _*)
        // create chart
        val chart = new LineChart[String, Number](xAxis, yAxis, ObservableBuffer(series1, series2))
// tooltip data
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

            // popup box
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
    end createLineChartTwoSeries

}

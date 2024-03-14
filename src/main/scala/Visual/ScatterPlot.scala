package Visual

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import finaviaAPI.APIClient.*
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
    def createHourScatterPlot(): ScatterChart[String, Number]  = 
        val graphData = new GraphData()
        val flightData = getAllFlightData()

        val xAxis = new CategoryAxis()
        val yAxis = new NumberAxis()
        xAxis.label = "Time"
        yAxis.label = "Number of Flights"

        val hourDataArray: Array[(String, Int)] = graphData.flightPerHourData(flightData)

        val series = new XYChart.Series[String, Number]()
        series.setName("Number of Flights")

        // Convert Array to ObservableBuffer
        series.data = ObservableBuffer(hourDataArray.map(cca => XYChart.Data[String, Number](cca._1, cca._2)): _*)

        // Return the ScatterChart with xAxis, yAxis, and series
        new ScatterChart[String, Number](xAxis, yAxis, ObservableBuffer(series))

    end createHourScatterPlot
}

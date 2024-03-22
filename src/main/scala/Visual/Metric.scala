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
import scalafx.scene.control.Label
import scalafx.scene.paint.Color
import scalafx.geometry.Pos
import scalafx.geometry.Pos.Center
import scalafx.scene.layout.{VBox, StackPane}
import scalafx.scene.layout.StackPane.*
import scalafx.scene.Node



class Metric extends VBox{
    def makeMetric(name: String, initialValue: String): Node = {
        val nameLabel = new Label(name)
        val valueLabel = new Label(initialValue)

        // Customize labels' appearance
        nameLabel.style = "-fx-font-weight: bold; -fx-font-size: 40px"
        valueLabel.style = "-fx-font-size: 100px"

        // Align labels within the VBox
        nameLabel.alignmentInParent = Pos.Center
        valueLabel.alignmentInParent = Pos.Center

        // Create a rectangle with a background color
        val rect = new Rectangle {
        width = 300 // Adjust the width as needed
        height = 200 // Adjust the height as needed
        fill = Color.LightBlue // Adjust the color as needed
        arcWidth = 10
        arcHeight = 1
        }

        val vbox = new VBox(nameLabel, valueLabel)
        vbox.alignment = Pos.Center

        val card = new StackPane
        card.children.addAll(rect, vbox)

        card // Return the StackPane as a Node
    }
}

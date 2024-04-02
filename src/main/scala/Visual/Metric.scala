package Visual

import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import scalafx.scene.control.Label
import scalafx.scene.layout.StackPane
import scalafx.scene.Node
import javafx.scene.input.MouseEvent
import scalafx.scene.paint.Color
import scalafx.geometry.Pos
import javafx.event.EventHandler
import scalafx.scene.transform.Translate
import scalafx.scene.layout.VBox
import scalafx.scene.layout.BorderPane
import scalafx.geometry.Pos.TopRight
import scalafx.geometry.Point2D
import scalafx.Includes.jfxMouseEvent2sfx

class Metric {
  def makeMetric(name: String, initialValue: String): Node = {
    val nameLabel = new Label(name)
    val valueLabel = new Label(initialValue)

    

    // Create a rectangle with a background color
    val rect = new Rectangle {
      width = 300 // Adjust the width as needed
      height = 200 // Adjust the height as needed
      fill = Color.LightBlue // Adjust the color as needed
      arcWidth = 10
      arcHeight = 1
      layoutX = 300
      layoutY = 200        
    }
    // Customize labels' appearance
    nameLabel.style = ("-fx-font-weight: bold; -fx-font-size: 40px")
    valueLabel.style = ("-fx-font-size: 100px")

    // Align labels within the VBox
    nameLabel.alignmentInParent = Pos.Center
    valueLabel.alignmentInParent = Pos.Center

    var vbox = new VBox(nameLabel, valueLabel)
    vbox.alignment = Pos.Center

    val card = new StackPane
    card.children.addAll(rect, vbox)


    rect.translateX = -0.5 * rect.width()
    rect.translateY = -0.5 * rect.height()

    rect.onMouseDragged = (event: MouseEvent) => {
      rect.translateX = event.x - 0.5 * rect.width()
      rect.translateY = event.y - 0.5 * rect.height()
    }

    card
  }
}


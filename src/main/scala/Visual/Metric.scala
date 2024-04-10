package Visual

import scalafx.scene.layout.{Pane, VBox}
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import scalafx.scene.control.Label
import scalafx.scene.layout.StackPane
import scalafx.scene.Node
import javafx.scene.input.MouseEvent
import scalafx.scene.paint.Color
import scalafx.geometry.Pos
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.scene.control.Label
import scalafx.scene.layout.StackPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.layout.VBox
import scalafx.scene.layout.BorderPane
import scalafx.scene.transform.Translate
import scalafx.scene.layout.Pane
import scalafx.scene.layout.HBox
import scalafx.geometry.Pos.TopRight
import scalafx.scene.layout.Region
import scalafx.geometry.Point2D
import scalafx.scene.control.Tooltip

class Metric {
  private val maxMetrics = 4
  var metrics: List[StackPane] = Nil
  var selectedCard: Option[StackPane] = None


  def makeMetric(name: String, initialValue: String, color: Color): StackPane =
      val nameLabel = new Label(name)
      val valueLabel = new Label(initialValue)

      val rect = new Rectangle {
        width = 300
        height = 200
        fill = color
        arcWidth = 50
        arcHeight = 50
      }

      nameLabel.style = "-fx-font-weight: bold; -fx-font-size: 40px"
      valueLabel.style = "-fx-font-size: 60px"

      nameLabel.alignmentInParent = Pos.Center
      valueLabel.alignmentInParent = Pos.Center

      val vbox = new VBox(nameLabel, valueLabel)
      vbox.alignment = Pos.Center

      val card = new StackPane
      card.children.addAll(rect, vbox)

      card.onMouseClicked = (event: MouseEvent) => {
            if (selectedCard.contains(card)) {
              rect.stroke = Color.Black
              selectedCard = None
            } else {
              rect.stroke = Color.Transparent
              selectedCard = Some(card)
            }
          }
      var orgSceneX, orgSceneY = 0.0
      var offsetX, offsetY = 0.0
      var middleX = 0.0
      var middleY = 0.0
      var oldMiddleX = 0.0
      var oldMiddleY = 0.0

      /*card.onMousePressed = (event: MouseEvent) => {
        orgScreenX = event.screenX
        orgScreenY = event.screenY
      }

      card.onMouseDragged = (event: MouseEvent) => {
        offsetX = event.screenX  *0.3
        offsetY = event.screenY *0.3
        card.translateX = offsetX
        card.translateY = offsetY
    } */

      card.onMousePressed = (event: MouseEvent) => {
          orgSceneX = event.sceneX
          orgSceneY = event.sceneY
          oldMiddleX = middleX
          oldMiddleY = middleY
        }

        card.onMouseDragged = (event: MouseEvent) => {
          offsetX = event.sceneX - orgSceneX
          //println("offsetY:" + offsetX) 
          offsetY = event.sceneY - orgSceneY
          //println("offsetY:" + offsetY) 
          
          val newTranslateX = offsetX + card.translateX() + oldMiddleX
          val newTranslateY = offsetY + card.translateY() + oldMiddleY
          //println("newtranslatex:" + newTranslateX)
          //println("newtranslatey" + newTranslateX)
          card.setLayoutX(newTranslateX) 
          card.setLayoutY(newTranslateY) 
          middleX = newTranslateY
          middleY = newTranslateY
        }



      val pointNode: scalafx.scene.Node = card
      val pointValue = name
      val pointTime = initialValue
      val tooltip = new Tooltip()
      tooltip.setText(pointValue + "\n" + pointTime.toString)
      tooltip.setStyle("-fx-background-color: lightgrey; " + "-fx-text-fill: black; ")
      Tooltip.install(pointNode, tooltip)

      card

  

}
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
  var cards: Option[StackPane] = None
  var occupied = false
  var counter = 0

  def makeMetric(name: String, initialValue: String, color: Color): StackPane =
      val nameLabel = new Label(name)
      val valueLabel = new Label(initialValue)

      val rect = new Rectangle {
        width = 330
        height = 220
        fill = color
        arcWidth = 50
        arcHeight = 50
      }

      nameLabel.style = "-fx-font-weight: bold; -fx-font-size: 35px"
      valueLabel.style = "-fx-font-size: 55px"

      nameLabel.alignmentInParent = Pos.Center
      valueLabel.alignmentInParent = Pos.Center

      val vbox = new VBox(nameLabel, valueLabel)
      vbox.alignment = Pos.Center

      val card = new StackPane
      card.children.addAll(rect, vbox)

         
      var orgSceneX, orgSceneY = 0.0
      var offsetX, offsetY = 0.0
      var middleX = 0.0
      var middleY = 0.0
      var oldMiddleX = 0.0
      var oldMiddleY = 0.0

      card.onMousePressed = (event: MouseEvent) => 
        if selectedCard == None then
          rect.stroke = Color.Black
          selectedCard = Some(card) 
        else
          rect.stroke = Color.Transparent
          selectedCard = None
        orgSceneX = event.sceneX
        orgSceneY = event.sceneY
        oldMiddleX = middleX
        oldMiddleY = middleY
            
        event.consume()        

      card.onMouseDragged = (event: MouseEvent) => 
          rect.stroke = Color.Black

          offsetX = event.sceneX - orgSceneX
          offsetY = event.sceneY - orgSceneY
            
          val newTranslateX = offsetX + card.translateX() + oldMiddleX
          val newTranslateY = offsetY + card.translateY() + oldMiddleY
          card.setLayoutX(newTranslateX) 
          card.setLayoutY(newTranslateY) 
          middleX = newTranslateY
          middleY = newTranslateY

      val pointNode: scalafx.scene.Node = card
      val pointValue = name
      val pointTime = initialValue
      val tooltip = new Tooltip()
      tooltip.setText(pointValue + "\n" + pointTime.toString)
      tooltip.setStyle("-fx-background-color: lightgrey; " + "-fx-text-fill: black; ")
      Tooltip.install(pointNode, tooltip)

      cards = Some(card)
      card

  

}
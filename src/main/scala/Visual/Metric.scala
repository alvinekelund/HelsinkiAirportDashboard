package Visual

import scalafx.scene.layout.{VBox}
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import scalafx.scene.control.Label
import scalafx.scene.layout.StackPane
import scalafx.scene.Node
import javafx.scene.input.MouseEvent
import scalafx.scene.paint.Color
import scalafx.geometry.Pos
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.scene.control.Tooltip

class Metric {
  var selectedCard: Option[StackPane] = None
  var cards: Option[StackPane] = None

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
// creates a stackpane with the textbox on the rectangle
      val card = new StackPane
      card.children.addAll(rect, vbox)
      var orgSceneX, orgSceneY = 0.0
      var offsetX, offsetY = 0.0
      var middleX, middleY = 0.0
      var oldMiddleX, oldMiddleY = 0.0
// movement logic
      card.onMousePressed = (event: MouseEvent) => 
        // Selecting cards
        if selectedCard == None then
          rect.stroke = Color.Black
          selectedCard = Some(card) 
        else
          rect.stroke = Color.Transparent
          selectedCard = None
          //Location
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
          // Stores the old location since it otherwise zeros it out
          middleX = newTranslateY
          middleY = newTranslateY
// Tooltip implementation
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
package Visual

import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.layout.Pane
import javafx.scene.input.MouseEvent
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.scene.Scene
import scalafx.scene.control.SplitPane

class RectangleTool {
    val metric = new Metric
    var backgroundClicked: Boolean = false // Flag to track if the background is clicked
    def makeRectangle(x: SplitPane): Rectangle = {
        val selectionRectangle = new Rectangle {
            stroke = Color.Blue // Set the border color
            strokeWidth = 1.0 // Set the border width
            fill = Color.rgb(0, 0, 255, 0.1) // Set the fill color with transparency
            visible = false // Initially, the rectangle is not visible
        }

        // Add mouse event handlers to track the selection
        var startX = 0.0
        var startY = 0.0
        x.onMousePressed = (event: MouseEvent) => {
            startX = event.sceneX
            startY = event.sceneY
            println(startX)
            println(startY)
            selectionRectangle.x = startX
            selectionRectangle.y = startY 
            selectionRectangle.width = 1.0
            selectionRectangle.height = 1.0
            selectionRectangle.visible = true
        }

        x.onMouseDragged = (event: MouseEvent) => {
            val endX = event.sceneX
            val endY = event.sceneY 
            println(endX)
            println(endY)

            selectionRectangle.width = (endX - startX).abs
            selectionRectangle.height = (endY - startY).abs
            selectionRectangle.x = math.min(startX, endX)
            println(selectionRectangle.x)
            selectionRectangle.y = math.min(startY, endY)
            println(selectionRectangle.y)

        }

        x.onMouseReleased = (event: MouseEvent) => {
            selectionRectangle.visible = false
            
            // Iterate through each metric card to check for intersection
            for (card <- metric.metrics) {
                val rect = card.getChildren.get(0).asInstanceOf[Rectangle]
                val cardBounds = rect.getBoundsInParent
                
                // Check for intersection between selection rectangle and card bounds
                val intersection = selectionRectangle.getBoundsInParent.intersects(cardBounds)
                
                // If intersection is detected, set the stroke color of the card to black
                if (intersection) {
                    rect.stroke = Color.Black
                }
            }
            
            // Here you can perform actions based on the selected area
            // For example, find data points within the selected area and highlight them
        }


        selectionRectangle // Return the created rectangle
    }

}

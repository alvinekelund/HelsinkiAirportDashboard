package Visual

import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.layout.Pane
import javafx.scene.input.MouseEvent
import scalafx.Includes.jfxMouseEvent2sfx

class RectangleTool {
    var parentPane: Pane = new Pane
    def makeRectangle(): Rectangle = {
        val selectionRectangle = new Rectangle {
            stroke = Color.Blue // Set the border color
            strokeWidth = 1.0 // Set the border width
            fill = Color.rgb(0, 0, 255, 0.1) // Set the fill color with transparency
            visible = false // Initially, the rectangle is not visible
        }

        // Add mouse event handlers to track the selection
        var startX = 0.0
        var startY = 0.0
        parentPane.onMousePressed = (event: MouseEvent) => {
            startX = event.sceneX
            startY = event.sceneY - 88
            selectionRectangle.x = startX
            selectionRectangle.y = startY
            selectionRectangle.width = 1.0
            selectionRectangle.height = 1.0
            selectionRectangle.visible = true
        }

        parentPane.onMouseDragged = (event: MouseEvent) => {
            val endX = event.sceneX
            val endY = event.sceneY
            selectionRectangle.width = (endX - startX).abs
            selectionRectangle.height = (endY - startY).abs
            selectionRectangle.x = math.min(startX, endX)
            selectionRectangle.y = math.min(startY, endY - 88)
        }

        parentPane.onMouseReleased = (event: MouseEvent) => {
            selectionRectangle.visible = false
            // Here you can perform actions based on the selected area
            // For example, find data points within the selected area and highlight them
        }

        selectionRectangle // Return the created rectangle
    }

}

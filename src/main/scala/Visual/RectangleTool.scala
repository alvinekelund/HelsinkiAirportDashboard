package Visual

import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.layout.Pane
import javafx.scene.input.MouseEvent
import scalafx.Includes.jfxMouseEvent2sfx


class RectangleTool {
    // offset depending on i which pane its created
    var offsetX = 0.0
    var offsetY = 0.0
    var backgroundClicked: Boolean = false
    var parentPane: Option[Pane] = None
    def makeRectangle(x: Pane): Rectangle = 
        parentPane = Some(x)
        val selectionRectangle = new Rectangle {
            stroke = Color.Blue
            strokeWidth = 1.0 
            fill = Color.rgb(0, 0, 255, 0.1)
            visible = false 
        }
        var startX = 0.0
        var startY = 0.0

// logic for the drawing and visibility state of the rectangle
        x.onMousePressed = (event: MouseEvent) => 
            backgroundClicked = true
            startX = event.sceneX - offsetX
            startY = event.sceneY - offsetY
            selectionRectangle.x = startX 
            selectionRectangle.y = startY
            selectionRectangle.width = 1.0
            selectionRectangle.height = 1.0
            selectionRectangle.visible = true
            event.consume()
            

        x.onMouseDragged = (event: MouseEvent) => 
            backgroundClicked = true
            val endX = event.sceneX - offsetX
            val endY = event.sceneY - offsetY
            selectionRectangle.width = (endX - startX).abs
            selectionRectangle.height = (endY - startY).abs
            selectionRectangle.x = math.min(startX, endX)
            selectionRectangle.y = math.min(startY, endY)
            event.consume()
        

        x.onMouseReleased = (event: MouseEvent) => 
            selectionRectangle.visible = false
        
        selectionRectangle
}
    
            

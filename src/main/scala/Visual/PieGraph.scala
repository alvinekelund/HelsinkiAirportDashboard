package Visual

import scalafx.scene.Scene
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.scene.chart.PieChart
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent
import scalafx.stage.Stage
import scalafx.stage.Modality
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Label
import javafx.scene.control.Tooltip






class PieGraph {
  def createPieChart(dataSet: Array[(String, Int)], x: String, y: String, label: String, color: Color): PieChart  = {
    val pieChartData = dataSet.map { case (label, value) => PieChart.Data(label, value) }
    val buffer = ObservableBuffer.from(pieChartData)    
    val pieChart = new PieChart {
      title = label
      data = buffer
    }
    //create new piechart
    val chart = pieChart
// the tooltip logic
    val total = chart.getData.foldLeft(0.0) {(x, y) => x + y.getPieValue}    
    chart.getData.foreach { d =>
      val sliceNode = d.getNode
      val pieValue = d.getPieValue
      val percent = (pieValue / total) * 100

      val tt = new Tooltip()
      tt.setText("%s: %.0f (%.2f%%)".format(d.getName, pieValue, percent))
      tt.setStyle("-fx-background-color: lightgrey; -fx-text-fill: black; ")
      Tooltip.install(sliceNode, tt)
// popup box
      sliceNode.setOnMouseClicked((event: MouseEvent) => {
        val dialog = new Stage {
          initModality(Modality.ApplicationModal)
          title = "Data Point Info"
          scene = new Scene {
          content = new BorderPane {
          center = new Label("%s: %.0f (%.2f%%)".format(d.getName, pieValue, percent))
          }
        }
      }
        dialog.showAndWait()
      })
    
    }
    chart
  }
}
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import finaviaAPI.APIClient.*
import scalafx.scene.control._
import scalafx.Includes._
import scalafx.event.ActionEvent


object Dashboard extends JFXApp3:

  def start() =

    stage = new JFXApp3.PrimaryStage:
      title = "Helsinki Airport Dashboar"
      width = 1500
      height = 900

    val root = Pane()

    val scene = Scene(parent = root)
    stage.scene = scene

    val button = new Button("Click me")
    button.layoutX = 100
    button.layoutY = 100

    val comboBox = new ComboBox(List("Table", "Graph", "Metric"))
    comboBox.layoutX = 200
    comboBox.layoutY = 100

    val listView = new ListView(List("Scalafx", "pydton"))
    listView.layoutX = 100
    listView.layoutY = 150
  

    root.children += (button, comboBox, listView)

    button.onAction = (e: ActionEvent) => {
      val selected = listView.selectionModel.apply().getSelectedItems
      listView.items = listView.items.apply().dif(selected)
      println("Button clicked")
    }

    comboBox.onAction = (e: ActionEvent) => {
      listView.items.apply() += comboBox.selectionModel.apply().getSelectedItem
    }
    
    // getFlightData()

  end start

end Main


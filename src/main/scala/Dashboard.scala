import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import finaviaAPI.APIClient.*
import scalafx.scene.control._
import scalafx.Includes._
import scalafx.event.ActionEvent
import javafx.scene.layout.VBox
import java.awt.Graphics
import scalafx.stage.StageStyle.Unified
import javafx.stage.StageStyle


object Dashboard extends JFXApp3:

  def start() =

    stage = new JFXApp3.PrimaryStage:
      title = "Helsinki Airport Dashboard"
      width = 1500
      height = 900
      initStyle(StageStyle.DECORATED)

    val root = Pane()
    val scene = Scene(parent = root)
    stage.scene = scene


    // val vbox = new VBox(10, new Button("Home"), new Button("Settings"))



    val menuBar = new MenuBar
    val fileMenu = new Menu("File")
    val settingsMenu = new Menu("Settings")
    val exitItem = new MenuItem("Exit")
    val colorItem = new MenuItem("Color")
    fileMenu.items = List(exitItem)
    settingsMenu.items = List(colorItem)
    menuBar.menus = List(fileMenu, settingsMenu)
    menuBar.prefWidth = 1500

    
    val tabPane = new TabPane
    val homeTab = new Tab
    homeTab.text = "Home"
    val dataTab = new Tab
    dataTab.text = "Data"
    tabPane.layoutY = 30
    tabPane.minWidth = 1500
    tabPane.tabs = List(homeTab, dataTab)



    /*
    val button = new Button("Click me")
    button.layoutX = 100
    button.layoutY = 100

    val comboBox = new ComboBox(List("Table", "Graph", "Metric"))
    comboBox.layoutX = 200
    comboBox.layoutY = 100

    val listView = new ListView(List("Scalafx", "pydton"))
    listView.layoutX = 100
    listView.layoutY = 150
  

    

    button.onAction = (e: ActionEvent) => {
      val selected = listView.selectionModel.apply().getSelectedItems
      listView.items = listView.items.apply().diff(selected)
      println("Button clicked")
    }

    comboBox.onAction = (e: ActionEvent) => {
      listView.items.apply() += comboBox.selectionModel.apply().getSelectedItem
    }
    */
    //getFlightData()

    root.children += (menuBar, tabPane)

  end start

end Dashboard


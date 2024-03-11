
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
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty}
import finaviaAPI.Flight
import Visual.GraphData
import scalafx.scene.chart._
import java.util.Locale.Category


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

    
 
    val tableView = new TableView(getFlightData())
    tableView.minHeight = 900
    val col1 = new TableColumn[Flight, String]("Flight Number")
    col1.cellValueFactory = cdf => StringProperty(cdf.value.fltnr)
    val col2 = new TableColumn[Flight, String]("Departure Time")
    col2.cellValueFactory = cdf => StringProperty(cdf.value.sdt)
    val col3 = new TableColumn[Flight, String]("Date")    
    col3.cellValueFactory = cdf => StringProperty(cdf.value.sdate)
    val col4 = new TableColumn[Flight, String]("Route")
    col4.cellValueFactory = cdf => StringProperty(cdf.value.route_1)
    val col5 = new TableColumn[Flight, String]("Route")
    col5.cellValueFactory = cdf => StringProperty(cdf.value.route_n_1)
    val col6 = new TableColumn[Flight, String]("Aircraft Registration")
    col6.cellValueFactory = cdf => StringProperty(cdf.value.acreg)
    val col7 = new TableColumn[Flight, String]("Aircraft Type")
    col7.cellValueFactory = cdf => StringProperty(cdf.value.actype)
    val col8 = new TableColumn[Flight, String]("Home Airport")
    col8.cellValueFactory = cdf => StringProperty(cdf.value.h_apt)
    val col9 = new TableColumn[Flight, String]("Callsign")
    col9.cellValueFactory = cdf => StringProperty(cdf.value.callsign)
    val col10 = new TableColumn[Flight, String]("Blt Area")
    col10.cellValueFactory = cdf => StringProperty(cdf.value.bltarea)
    

    tableView.columns ++= List(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10)

    val graphData = new GraphData()
    val flightData = getFlightData()
    val xAxis = new CategoryAxis()
    val yAxis = new NumberAxis()
    xAxis.label = "Airline"
    yAxis.label = "Number of Flights"
    val carrierCountArray: Array[(String, Int)] = graphData.perCarrier(flightData)
    val series = new XYChart.Series[String, Number]
    series.setName("Operated flights by airline")
    series.data = carrierCountArray.map(cca => XYChart.Data[String, Number](cca._1, cca._2))

    val makeChart = new BarChart[String, Number](xAxis, yAxis, ObservableBuffer(series))

    dataTab.content = tableView
    homeTab.content = makeChart
    





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

    root.children += (menuBar, tabPane, tableView)

  end start

end Dashboard


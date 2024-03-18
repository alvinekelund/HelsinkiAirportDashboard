import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.{KeyCodeCombination, KeyCode, KeyCombination}
import scalafx.scene.paint.Color._
import finaviaAPI.DataParser.*
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
import Visual.*
import Visual.LinePlot
import scalafx.scene.chart._
import java.util.Locale.Category
import scalafx.stage.FileChooser
import cats.conversions.all
import Visual.Tables
import cats.instances.list
import Visual.ScatterPlot
import finaviaAPI.DataParser
import scala.annotation.meta.field
import cats.instances.map
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import scalafx.geometry.Orientation




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
    val saveItem = new MenuItem("Save")
    val openItem = new MenuItem("Open")
    val exitItem = new MenuItem("Exit")
    val colorItem = new MenuItem("Color")
    fileMenu.items = List(openItem, saveItem, new SeparatorMenuItem, exitItem)
    settingsMenu.items = List(colorItem)
    menuBar.menus = List(fileMenu, settingsMenu)
    menuBar.prefWidth = 1500

    openItem.accelerator = new KeyCodeCombination(KeyCode.O, KeyCombination.ControlDown)
    saveItem.accelerator = new KeyCodeCombination(KeyCode.S, KeyCombination.ControlDown)
    exitItem.accelerator = new KeyCodeCombination(KeyCode.X, KeyCombination.ControlDown)

    exitItem.onAction = (e: ActionEvent) => sys.exit(0)
    openItem.onAction = (e: ActionEvent) => {
      val fileChooser = new FileChooser
      val selectedFile = fileChooser.showOpenDialog(stage)
    }
/*
    val graphComboBox = new ComboBox(List("Column", "Scatter", "Line", "Pie"))
    graphComboBox.value = "Pie"

    val datasetComboBox = new ComboBox(List("Carrier", "Time"))
    datasetComboBox.value = "Time"

    val deparrComboBox = new ComboBox(List("All", "Departing", "Arriving"))
    deparrComboBox.value = "All"
*/


    val tabPane = new TabPane
    val homeTab = new Tab
    homeTab.text = "Home"
    val dataTab = new Tab
    dataTab.text = "Data"
    tabPane.layoutY = 30
    tabPane.minWidth = 1500
    tabPane.tabs = List(homeTab, dataTab)

    val tabPane1 = new TabPane
    val depTab = new Tab
    val arrTab = new Tab
    val allTab = new Tab
    depTab.text = "Departing"
    arrTab.text = "Arriving"
    allTab.text = "All"
    tabPane1.layoutY = 30
    tabPane1.minWidth = 1500
    tabPane1.tabs = List(allTab, depTab, arrTab)    

    val tables = new Tables
    allTab.content = tables.createFlightTableAll()
    depTab.content = tables.createFlightTableDep()
    arrTab.content = tables.createFlightTableArr()
    
    def makeColumnGraph(graphDataType: Array[(String, Int)], x: String, y: String, label: String): BarChart[String, Number] =
      val columnChart = new ColumnChart()
      val chart = columnChart.createColumnChart(graphDataType, x, y, label)
      chart
    
    def makeScatterChart(graphDataType: Array[(String, Int)], x: String, y: String, label: String): ScatterChart[String, Number] = 
      val scatterChart = new ScatterPlot()
      val chart = scatterChart.createScatterChart(graphDataType, x, y, label)
      chart

    def makeLineChart(graphDataType: Array[(String, Int)], x: String, y: String, label: String): LineChart[String, Number] = 
      val lineChart = new LinePlot()
      val chart = lineChart.createLineChart(graphDataType, x, y, label)
      chart

    def makePieChart(graphDataType: Array[(String, Int)], x: String, y: String, label: String): PieChart = 
      val pieChart = new PieGraph()
      val chart = pieChart.createPieChart(graphDataType, x, y, label)
      chart

    

    def makeChart(graphType: String, dataType: Array[(String, Int)], x: String, y: String, label: String) = 
      graphType match
        case "Column" => makeColumnGraph(dataType, x, y, label)
        case "Scatter" => makeScatterChart(dataType, x, y, label)
        case "Line" => makeLineChart(dataType, x, y, label)
        case "Pie" => makePieChart(dataType, x, y, label)
        case _ => throw new IllegalArgumentException("Invalid graph type")

    val graphData = new GraphData
    var depArrData = getAllFlightData()

    def getChartData(dataset: String): Tuple4[Array[(String, Int)], String, String, String] = dataset match {
      case "Time" => Tuple4(graphData.flightPerHourData(depArrData), "Time", "Airplanes flown", "Airplanes flown each hour")
      case "Carrier" => Tuple4(graphData.flightPerCarrierData(depArrData), "Carrier", "Airplanes flown", "Amount of planes flown by carrier")
      case _ => throw new IllegalArgumentException("Invalid dataset")
    }

    val defaultChart = makeChart("Pie", graphData.flightPerHourData(getAllFlightData()), "Time", "Airplanes flown", "Airplanes flown each hour")


    
    def initializeGraph(): (ComboBox[String], ComboBox[String], ComboBox[String], VBox) = {
      val graphComboBox = new ComboBox(List("Column", "Scatter", "Line", "Pie"))
      graphComboBox.value = "Pie"

      val datasetComboBox = new ComboBox(List("Carrier", "Time"))
      datasetComboBox.value = "Time"

      val deparrComboBox = new ComboBox(List("All", "Departing", "Arriving"))
      deparrComboBox.value = "All"

      val comboBoxHBox = new HBox(10, graphComboBox, datasetComboBox, deparrComboBox)
      val chartVBox = new VBox(defaultChart)

      graphComboBox.onAction = () => updateChart()
      datasetComboBox.onAction = () => updateChart()
      deparrComboBox.onAction = () => updateChart()

      def updateChart(): Unit = {
        val selectedDataset = datasetComboBox.value.value
        val selectedGraph = graphComboBox.value.value
        val selectedDepArr = deparrComboBox.value.value
        depArrData = selectedDepArr match {
          case "All" => getAllFlightData()
          case "Departing" => getDepFlightData()
          case "Arriving" => getArrFlightData()
        }
        val newChart = makeChart(selectedGraph, getChartData(selectedDataset)._1, getChartData(selectedDataset)._2, getChartData(selectedDataset)._3, getChartData(selectedDataset)._4)
        chartVBox.children.clear()
        chartVBox.children.addAll(newChart)
      }

      (graphComboBox, datasetComboBox, deparrComboBox, new VBox(comboBoxHBox, chartVBox))
    }

    val (graphComboBox1, datasetComboBox1, deparrComboBox1, chartVBox1) = initializeGraph()
    val (graphComboBox2, datasetComboBox2, deparrComboBox2, chartVBox2) = initializeGraph()

    val split = new SplitPane
    split.orientation = Orientation.Horizontal
    split.items.addAll(chartVBox1, chartVBox2)
    
    homeTab.content = split
    dataTab.content = tabPane1

    
    root.children += (menuBar, tabPane)

  end start

end Dashboard


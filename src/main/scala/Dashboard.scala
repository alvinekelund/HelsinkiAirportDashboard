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
import scalafx.scene.layout.HBox
import scalafx.geometry.Orientation
import scalafx.scene.layout.Priority
import scalafx.scene.control.ComboBox




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

    val card = new Metric
    val graphData = new GraphData
    val metricData = new MetricData
    var depArrData = getAllFlightData()

    def getChartData(dataset: String): Tuple4[Array[(String, Int)], String, String, String] = dataset match {
      case "Time" => Tuple4(graphData.flightPerHourData(depArrData), "Time", "Airplanes flown", "Airplanes flown each hour")
      case "Carrier" => Tuple4(graphData.flightPerCarrierData(depArrData), "Carrier", "Airplanes flown", "Amount of planes flown by carrier")
      case _ => throw new IllegalArgumentException("Invalid dataset")
    }

    def getMetricData(dataset: String): Tuple2[String, String] = dataset match {
      case "Amount" => Tuple2(metricData.totalFlights(depArrData), "Total amount of planes")
    }
    
    val defaultChart = makeChart("Pie", graphData.flightPerHourData(getAllFlightData()), "Time", "Airplanes flown", "Airplanes flown each hour")
    

    def initializeGraph(): HBox = {
      val deparrComboBoxC = new ComboBox(List("All", "Departing", "Arriving"))
      deparrComboBoxC.value = "All"

      val deparrComboBoxM = new ComboBox(List("All", "Departing", "Arriving"))
      deparrComboBoxM.value = "All"

      val viewComboBox = new ComboBox(List("Graph View", "Metric View"))
      viewComboBox.value = "Graph View"


      val graphComboBox = new ComboBox(List("Column", "Scatter", "Line", "Pie"))
      graphComboBox.value = "Column"

      val datasetComboBoxC = new ComboBox(List("Carrier", "Time"))
      datasetComboBoxC.value = "Time"
      
      val datasetComboBoxM = new ComboBox(List("Amount"))
      datasetComboBoxM.value = "Amount"

      val comboBoxHBox1 = new HBox(10, graphComboBox, datasetComboBoxC, deparrComboBoxC)  
      val comboBoxHBox2 = new HBox(10, datasetComboBoxM, deparrComboBoxM)
           
      val chartVBox = new VBox(comboBoxHBox1, defaultChart)
      graphComboBox.onAction = () => updateChart()
      datasetComboBoxC.onAction = () => updateChart()
      datasetComboBoxM.onAction = () => updateChart()
      deparrComboBoxC.onAction = () => updateChart()
      deparrComboBoxM.onAction = () => updateChart()
      viewComboBox.onAction = () => updateChart()
      def updateChart(): Unit = 
          var selectedDepArrC = deparrComboBoxC.value.value
          var selectedDepArrM = deparrComboBoxM.value.value
          var selectedView = viewComboBox.value.value
          var selectedDatasetC = datasetComboBoxC.value.value
          var selectedDatasetM = datasetComboBoxM.value.value
          var selectedGraph = graphComboBox.value.value
          
          
          depArrData = selectedDepArrC match 
            case "All" => getAllFlightData()
            case "Departing" => getDepFlightData()
            case "Arriving" => getArrFlightData()
          
          val newContent = selectedView match 
            case "Graph View" =>
              (new VBox(comboBoxHBox1, makeChart(selectedGraph, getChartData(selectedDatasetC)._1, getChartData(selectedDatasetC)._2,
                getChartData(selectedDatasetC)._3, getChartData(selectedDatasetC)._4)))
            case "Metric View" =>
              (new VBox(comboBoxHBox2, card.makeMetric(getMetricData(selectedDatasetM)._2, getMetricData(selectedDatasetM)._1)))
              
          chartVBox.children.clear()
          chartVBox.children.add(newContent)

          
      
      new HBox(chartVBox, viewComboBox)
    }

    val HBox1 = initializeGraph()
    val HBox2 = initializeGraph()
    val HBox3 = initializeGraph()


    val split = new SplitPane
    split.orientation = Orientation.Horizontal
    val split1 = new SplitPane
    split1.orientation = Orientation.Vertical
    split1.items.addAll(HBox1, HBox3)
    split.items.addAll(split1, HBox2)
    
    homeTab.content = split
    dataTab.content = tabPane1
    
    root.children += (menuBar, tabPane)

  end start

end Dashboard


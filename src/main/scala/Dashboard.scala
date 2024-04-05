import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.{KeyCodeCombination, KeyCode, KeyCombination}
import scalafx.scene.paint.Color._
import Data.DataParser.*
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
import Data.Flight
import Visual.*
import Visual.LinePlot
import scalafx.scene.chart._
import java.util.Locale.Category
import scalafx.stage.FileChooser
import cats.conversions.all
import Visual.Tables
import cats.instances.list
import Visual.ScatterPlot
import Data.DataParser
import scala.annotation.meta.field
import cats.instances.map
import javafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.geometry.Orientation
import scalafx.scene.layout.Priority
import scalafx.scene.control.ComboBox
import scalafx.geometry.Pos.{TopRight, TopCenter}
import scalafx.geometry.Pos
import scalafx.scene.layout.StackPane
import finaviaAPI.*
import java.util.Timer
import java.util.TimerTask
import scala.concurrent.duration._
import scalafx.application.Platform
import scalafx.scene.input.MouseEvent
import javafx.scene.Node
import scala.math.*
import cats.conversions.all.autoNarrowContravariant
import cats.conversions.variance.autoNarrowContravariant
import cats.conversions.all.autoWidenFunctor
import cats.conversions.variance.autoWidenFunctor






object Dashboard extends JFXApp3:

  def start() =

    

    stage = new JFXApp3.PrimaryStage:
      title = "Helsinki Airport Dashboard"
      width = 1500
      height = 1000
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
    val themeItem = new MenuItem("Theme")
    fileMenu.items = List(openItem, saveItem, new SeparatorMenuItem, exitItem)
    settingsMenu.items = List(themeItem)
    menuBar.menus = List(fileMenu, settingsMenu)
    menuBar.prefWidth = 1500

    openItem.accelerator = new KeyCodeCombination(KeyCode.O, KeyCombination.ControlDown)
    saveItem.accelerator = new KeyCodeCombination(KeyCode.S, KeyCombination.ControlDown)
    exitItem.accelerator = new KeyCodeCombination(KeyCode.X, KeyCombination.ControlDown)

      

    var isDarkMode = true

    themeItem.onAction = (e: ActionEvent) => toggleTheme()

    def toggleTheme(): Unit = {
    isDarkMode = !isDarkMode
    if (isDarkMode) {
      stage.scene.value.stylesheets = List(getClass.getResource("/dark-theme.css").toExternalForm)
    } else {
      stage.scene.value.stylesheets = List(getClass.getResource("/light-theme.css").toExternalForm)
    }
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

    def getChartData(dataset: String, depArr: ObservableBuffer[Flight]): Tuple4[Array[(String, Int)], String, String, String] = dataset match {
            case "Time" => Tuple4(graphData.flightPerHourData(depArr), "Time", "Airplanes flown", "Airplanes flown each hour")
            case "Carrier" => Tuple4(graphData.flightPerCarrierData(depArr), "Carrier", "Airplanes flown", "Amount of planes flown by carrier")
            case _ => throw new IllegalArgumentException("Invalid dataset")
          }

    def getMetricData(dataset: String, depArr: ObservableBuffer[Flight]): Tuple2[String, String] = dataset match {
            case "Amount" => Tuple2(metricData.totalFlights(depArr), "Planes")
          }
    
    



  
    var chart: Option[ScatterChart[String, Number]] = None


    def initializeGraph(): VBox = {
      
      var chart: Option[ScatterChart[String, Number]] = None
      var removeAdd = 0
      var hideShow = 2 
      var remainder = 0
      var parentPane: Pane = new Pane
      var visible: VBox = new VBox(0) 
      val metric = new Metric


      def addMetric(card: StackPane): Pane = {
        parentPane.getChildren().clear()
        parentPane.getChildren().addAll(card)
        parentPane

      }

      def removeMetric(): Pane = {
        parentPane.getChildren().clear()
        parentPane
      }
    
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

      val removeButton = new Button("Remove")
      val addButton = new Button("Add")
      val hideButton = new ToggleButton("Hide")
      val viewBoxHBox = new HBox(10, hideButton, viewComboBox)

      val comboBoxHBox1 = new HBox(10, graphComboBox, datasetComboBoxC, deparrComboBoxC)  
      val comboBoxHBox2 = new HBox(10, datasetComboBoxM, deparrComboBoxM, removeButton, addButton)
           
      
      

      
      var changeInfo = 0
      var newCBoxes = comboBoxHBox1
      var loaded = 0
      val border = new BorderPane
      border.center = newCBoxes
      border.right = viewBoxHBox
      border.bottom = visible
      def updateChart(): Unit = 
          
          var selectedDepArrC = deparrComboBoxC.value.value
          var selectedDatasetC = datasetComboBoxC.value.value
          var selectedView = viewComboBox.value.value
          var selectedGraph = graphComboBox.value.value
          
          if loaded == 0 then
            depArrData = selectedDepArrC match 
              case "All" => getAllFlightData()
              case "Departing" => getDepFlightData()
              case "Arriving" => getArrFlightData()

          val newChart = 
              new VBox(makeChart(selectedGraph, getChartData(selectedDatasetC, depArrData)._1, 
                getChartData(selectedDatasetC, depArrData)._2,
                getChartData(selectedDatasetC, depArrData)._3,
                getChartData(selectedDatasetC, depArrData)._4))
  
          newCBoxes.children.clear()
          newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC)
          visible = newChart
  
          border.center = newCBoxes
          border.right = viewBoxHBox
          border.bottom = visible

      def updateMetrics(): Unit =
            
          var selectedDatasetM = datasetComboBoxM.value.value
          var selectedDepArrM = deparrComboBoxC.value.value
          var selectedView = viewComboBox.value.value

          if loaded == 0 then
            depArrData = selectedDepArrM match 
              case "All" => getAllFlightData()
              case "Departing" => getDepFlightData()
              case "Arriving" => getArrFlightData()
          val newestMetric = metric.makeMetric(getMetricData(selectedDatasetM, depArrData)._2, getMetricData(selectedDatasetM, depArrData)._1)

          val newMetric =
            if changeInfo == 0 then
                if removeAdd == 1 then 
                  new VBox(addMetric(newestMetric))
                else
                  new VBox(removeMetric())
            else
                new VBox(addMetric(newestMetric))
          

          changeInfo = 0
          newCBoxes.children.clear()
          newCBoxes.children.addAll(datasetComboBoxM, deparrComboBoxM, removeButton, addButton)
          visible = newMetric

          border.center = newCBoxes
          border.right = viewBoxHBox
          border.bottom = visible


      
      graphComboBox.onAction = () => updateChart()
      datasetComboBoxC.onAction = () => updateChart()
      datasetComboBoxM.onAction = () => 
        changeInfo = 1
        updateMetrics()
      deparrComboBoxC.onAction = () => updateChart()
      deparrComboBoxM.onAction = () => 
        changeInfo = 1
        updateMetrics()
      viewComboBox.onAction = () => 
        if (viewComboBox.value.value == "Graph View") then updateChart()
        else updateMetrics()
      removeButton.onAction = (e: ActionEvent) => {
        removeAdd = 0
        updateMetrics()
      }

      addButton.onAction = (e: ActionEvent) => {
        println("dke")
        removeAdd = 1
        updateMetrics()
      }
      hideButton.onAction = () => {
        visible.visible = !visible.visible.value
      }
      exitItem.onAction = (e: ActionEvent) => sys.exit(0)
      saveItem.onAction = (e: ActionEvent) => {
      val fileChooser = new FileChooser
      val selectedFile = fileChooser.showSaveDialog(stage)
      if (selectedFile != null) {
      val writer = new java.io.PrintWriter(selectedFile)
      writer.write(xmlFlightData())
      writer.close()
        }
      }
      openItem.onAction = (e: ActionEvent) => {
        val fileChooser = new FileChooser
        val selectedFile = fileChooser.showOpenDialog(stage)
        if (selectedFile != null) {
          try {
            val fileContent = scala.io.Source.fromFile(selectedFile).mkString
            depArrData = getLoadedFlightData("all", fileContent)
            loaded = 1
            updateChart()
          } catch {
            case ex: Exception => ex.printStackTrace() 
          }
        } 
      }
      if loaded == 0 then
        val timer = new Timer(true)
        val interval = 1.minute.toMillis

        timer.scheduleAtFixedRate(new TimerTask {
          def run(): Unit = {
            Platform.runLater(() => 
              if viewComboBox.value.value == "Graph View" then updateChart()
              else updateMetrics())
          }
        }, 0, interval)
        border.center = newCBoxes
        border.right = viewBoxHBox
        border.bottom = visible
        

      new VBox(border)

    }
      

    val VBox1 = initializeGraph()
    val VBox2 = initializeGraph()
    val VBox3 = initializeGraph()
    val VBox4 = initializeGraph()

    val split = new SplitPane
    split.orientation = Orientation.Horizontal

    val split1 = new SplitPane
    split1.orientation = Orientation.Vertical
    split1.items.addAll(VBox1, VBox3)

    val split2 = new SplitPane
    split2.orientation = Orientation.Vertical
    split2.items.addAll(VBox2, VBox4)
    split.items.addAll(split1, split2)

    homeTab.content = split
    dataTab.content = tabPane1

    root.children += (menuBar, tabPane)

  end start

end Dashboard


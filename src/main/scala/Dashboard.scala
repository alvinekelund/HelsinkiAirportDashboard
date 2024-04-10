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
 import scalafx.scene.paint.Color
 import scalafx.scene.layout.Background
 import scalafx.scene.layout.BackgroundFill






 object Dashboard extends JFXApp3:

  def start() =

    
    stage = new JFXApp3.PrimaryStage:
      title = ""
      width = 1500
      height = 970
      initStyle(StageStyle.DECORATED)

    val darkBackgroundColor = new Background(Array(new BackgroundFill(Color.rgb(60, 60, 60), null, null)))
    val lightBackgroundColor = new Background(Array(new BackgroundFill(Color.rgb(240, 240, 240), null, null)))

    val root = new Pane 
    val rectangleTool = new RectangleTool
    val scene = Scene(parent = root)

    stage.scene = scene

    val menuBar = new MenuBar
    val fileMenu = new Menu("File")
    val settingsMenu = new Menu("Settings")
    val saveItem = new MenuItem("Save")
    val openItem = new MenuItem("Open")
    val exitItem = new MenuItem("Exit")
    val liveItem = new MenuItem("Live")
    val themeItem = new MenuItem("Light/Dark")
    fileMenu.items = List(liveItem, openItem, saveItem, new SeparatorMenuItem, exitItem)
    settingsMenu.items = List(themeItem)
    menuBar.menus = List(fileMenu, settingsMenu)
    menuBar.prefWidth = 1500

    openItem.accelerator = new KeyCodeCombination(KeyCode.O, KeyCombination.ControlDown)
    liveItem.accelerator = new KeyCodeCombination(KeyCode.L, KeyCombination.ControlDown)
    saveItem.accelerator = new KeyCodeCombination(KeyCode.S, KeyCombination.ControlDown)
    exitItem.accelerator = new KeyCodeCombination(KeyCode.X, KeyCombination.ControlDown)

      

    
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

    var fileContent: String = ""
    var loaded = 0
    

    
// general methods used in initializegraph
   

    def makeTwoSeriesScatterChart(graphDataType: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String): ScatterChart[String, Number] =
      val scatterChart = new ScatterPlot()
      val chart = scatterChart.createScatterChartTwoSeries(graphDataType, x, y, label1, label2)
      chart

    def makeTwoSeriesColumnChart(graphDataType: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String): BarChart[String, Number] =
      val columnChart = new ColumnChart()
      val chart = columnChart.createColumnChartTwoSeries(graphDataType, x, y, label1, label2)
      chart

    def makeTwoSeriesLineChart(graphDataType: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String): LineChart[String, Number] =
      val lineChart = new LinePlot()
      val chart = lineChart.createLineChartTwoSeries(graphDataType, x, y, label1, label2)
      chart

    def makeColumnGraph(graphDataType: Array[(String, Int)], x: String, y: String, label: String, color: String): BarChart[String, Number] =
      val columnChart = new ColumnChart()
      val chart = columnChart.createColumnChart(graphDataType, x, y, label, color)
      chart
    
    def makeScatterChart(graphDataType: Array[(String, Int)], x: String, y: String, label: String, color: String): ScatterChart[String, Number] = 
      val scatterChart = new ScatterPlot()
      val chart = scatterChart.createScatterChart(graphDataType, x, y, label, color)
      chart

    def makeLineChart(graphDataType: Array[(String, Int)], x: String, y: String, label: String, color: String): LineChart[String, Number] = 
      val lineChart = new LinePlot()
      val chart = lineChart.createLineChart(graphDataType, x, y, label, color)
      chart

    def makePieChart(graphDataType: Array[(String, Int)], x: String, y: String, label: String, color: String): PieChart = 
      val pieChart = new PieGraph()
      val chart = pieChart.createPieChart(graphDataType, x, y, label, color)
      chart

    def makeChart(graphType: String, dataType: Array[(String, Int)], x: String, y: String, label: String, color: String) = 
      graphType match
        case "Column" => makeColumnGraph(dataType, x, y, label, color)
        case "Scatter" => makeScatterChart(dataType, x, y, label, color)
        case "Line" => makeLineChart(dataType, x, y, label, color)
        case "Pie" => makePieChart(dataType, x, y, label, color)
        case _ => throw new IllegalArgumentException("Invalid graph type")
    
    def makeDualChart(graphType: String, dataType: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String) = 
      graphType match
        case "Column" => makeTwoSeriesColumnChart(dataType, x, y, label1, label2)
        case "Scatter" => makeTwoSeriesScatterChart(dataType, x, y, label1, label2)
        case "Line" => makeTwoSeriesLineChart(dataType, x, y, label1, label2)
        case _ => throw new IllegalArgumentException("Invalid graph type")

    def createLoadedFlightTableAll(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent, "all")
    }
    def createLoadedFlightTableDep(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent, "dep")
    }
    def createLoadedFlightTableArr(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent, "arr")
    }

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
            case "Total (24h)" => Tuple2(metricData.totalFlights(depArr), "Total (24h)")
            case "Busiest Hour" => Tuple2(metricData.busiestHour(depArr), "Busiest Hour")
            case "Least Busy Hour" => Tuple2(metricData.leastBusyHour(depArr), "Least Busy Hour")
            case _ => throw new IllegalArgumentException("Invalid dataset")
          }
    

    def initializeGraph(): VBox = {

      val parentPane: Pane = new Pane

     
      // Add the selection rectangle to the root pane
     // root.children.add(selectionRectangle)
      var removeAdd = 1
      var visible: VBox = new VBox(0) 
      val metric = new Metric
      var c = 0


      def addMetric(card: StackPane): Pane = {
        parentPane.getChildren().clear()
        parentPane.getChildren().addAll(card)
        parentPane

      }

      def removeMetric(): Pane = {
        parentPane.getChildren().clear()
        parentPane
      }
    
      val deparrComboBoxC1 = new ComboBox(List("All", "Departing", "Arriving", "Separate"))
      deparrComboBoxC1.value = "All"

      val deparrComboBoxC2 = new ComboBox(List("All", "Departing", "Arriving"))
      deparrComboBoxC2.value = "All"

      val deparrComboBoxM = new ComboBox(List("All", "Departing", "Arriving"))
      deparrComboBoxM.value = "All"

      val viewComboBox = new ComboBox(List("Graph View", "Metric View"))
      viewComboBox.value = "Graph View"

      val graphComboBox = new ComboBox(List("Column", "Scatter", "Line", "Pie"))
      graphComboBox.value = "Column"

      val datasetComboBoxC = new ComboBox(List("Carrier", "Time"))
      datasetComboBoxC.value = "Time"

      val colorComboBoxC = new ComboBox(List("Red", "Green", "Blue", "Orange", "Black"))
      colorComboBoxC.value = "Red"
      
      val datasetComboBoxM = new ComboBox(List("Total (24h)", "Busiest Hour", "Least Busy Hour"))
      datasetComboBoxM.value = "Total (24h)"

      val colorComboBoxM = new ComboBox(List("LightBlue", "Orange", "Green", "Yellow", "Grey"))
      colorComboBoxM.value = "LightBlue"

      val removeButton = new Button("Remove")
      val addButton = new Button("Add")
      val hideButton = new ToggleButton("Hide")
      val viewBoxHBox = new HBox(10, hideButton, viewComboBox)
      // initial box, changes instantly
      val comboBoxHBox1 = new HBox(10, graphComboBox, datasetComboBoxC, deparrComboBoxC1, colorComboBoxC)  
      //val comboBoxHBox2 = new HBox(10, datasetComboBoxM, deparrComboBoxM, colorComboBox, removeButton, addButton)
           
      
      
      var changeInfo = 0
      var newCBoxes = comboBoxHBox1
      val border = new BorderPane
      border.center = newCBoxes
      border.right = viewBoxHBox
      border.bottom = visible
      def updateChart(): Unit = 
          stage.title = "Helsinki Airport Dashboard " + timeCalled

          
          var selectedDepArrC = 
            if c == 0 && deparrComboBoxC1.value.value != "Separate" then 
              deparrComboBoxC2.value.value = deparrComboBoxC1.value.value
              deparrComboBoxC1.value.value 
            else if c == 1 then
              deparrComboBoxC1.value.value = deparrComboBoxC2.value.value
              deparrComboBoxC2.value.value 
            else
              deparrComboBoxC1.value.value
          var selectedDatasetC = datasetComboBoxC.value.value
          var selectedGraph = graphComboBox.value.value
          var selectedColorC = colorComboBoxC.value.value
          
          if loaded == 1 then // Change the condition to check if the file is loaded
            depArrData = selectedDepArrC match 
              case "All" => getLoadedFlightData(fileContent, "all") // Retrieve data from loaded file
              case "Departing" => getLoadedFlightData(fileContent, "dep")
              case "Arriving" => getLoadedFlightData(fileContent, "arr")
              case _ => getLoadedFlightData(fileContent, "all")
          else 
            depArrData = selectedDepArrC match
              case "All" => getAllFlightData() // Retrieve data from default methods
              case "Departing" => getDepFlightData()
              case "Arriving" => getArrFlightData()
              case _ => getAllFlightData()
          val newChart = 
            if selectedDepArrC == "Separate" then
              if selectedDatasetC != "Time" || selectedGraph == "Pie" then
                new VBox(makeChart(selectedGraph, getChartData(selectedDatasetC, depArrData)._1, 
                                getChartData(selectedDatasetC, depArrData)._2,
                                getChartData(selectedDatasetC, depArrData)._3,
                                getChartData(selectedDatasetC, depArrData)._4, 
                                selectedColorC))              
              else 
                if loaded == 0 then
                  new VBox(makeDualChart(selectedGraph, graphData.flightPerHourDepArr(getSepFlightData()._1, getSepFlightData()._2), "Time", "Airplane Flown", "Departures", "Arrivals"))
                else
                  new VBox(makeDualChart(selectedGraph, graphData.flightPerHourDepArr(getLoadedFlightData(fileContent, "dep"), getLoadedFlightData(fileContent, "arr")), "Time", "Airplane Flown", "Departures", "Arrivals"))

            else 
              new VBox(makeChart(selectedGraph, getChartData(selectedDatasetC, depArrData)._1, 
                getChartData(selectedDatasetC, depArrData)._2,
                getChartData(selectedDatasetC, depArrData)._3,
                getChartData(selectedDatasetC, depArrData)._4,
                selectedColorC))
    
          newCBoxes.children.clear()
          if selectedDatasetC != "Time" || selectedGraph == "Pie" then
            c = 1
            newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC2, colorComboBoxC)
          else 
            c = 0
            newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC1, colorComboBoxC)
          visible = newChart
          border.center = newCBoxes
          border.right = viewBoxHBox
          border.bottom = visible

      def updateMetrics(): Unit =

          stage.title = "Helsinki Airport Dashboard " + timeCalled
            
          var selectedDatasetM = datasetComboBoxM.value.value
          var selectedDepArrM = deparrComboBoxM.value.value
          val selectedColor = colorComboBoxM.value.value

          if loaded == 1 then // Change the condition to check if the file is loaded
            depArrData = selectedDepArrM match 
              case "All" => getLoadedFlightData(fileContent, "all") // Retrieve data from loaded file
              case "Departing" => getLoadedFlightData(fileContent, "dep")
              case "Arriving" => getLoadedFlightData(fileContent, "arr")
          else 
            depArrData = selectedDepArrM match
              case "All" => getAllFlightData() // Retrieve data from default methods
              case "Departing" => getDepFlightData()
              case "Arriving" => getArrFlightData()



          val newestMetric = metric.makeMetric(getMetricData(selectedDatasetM, depArrData)._2, getMetricData(selectedDatasetM, depArrData)._1, selectedColor)

          val newMetric =
              if removeAdd == 0 then 
                removeAdd = 1
                new VBox(removeMetric())
              else
                new VBox(addMetric(newestMetric))

          newCBoxes.children.clear()
          newCBoxes.children.addAll(datasetComboBoxM, deparrComboBoxM, colorComboBoxM, removeButton, addButton)
          visible = newMetric
          border.center = newCBoxes
          border.right = viewBoxHBox
          border.bottom = visible

  
      
      graphComboBox.onAction = () => updateChart()
      datasetComboBoxC.onAction = () => updateChart()
      datasetComboBoxM.onAction = () => updateMetrics()
      deparrComboBoxM.onAction = () => updateMetrics()
      colorComboBoxM.onAction = () => updateMetrics()
      colorComboBoxC.onAction = () => updateChart()
      deparrComboBoxC1.onAction = () => 
        c = 0
        updateChart()
      deparrComboBoxC2.onAction = () => 
        c = 1
        updateChart()
      viewComboBox.onAction = () => 
        if (viewComboBox.value.value == "Graph View") then updateChart()
        else updateMetrics()
      removeButton.onAction = (e: ActionEvent) => {
        removeAdd = 0
        updateMetrics()
        
      }
      addButton.onAction = (e: ActionEvent) => {
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
            fileContent = scala.io.Source.fromFile(selectedFile).mkString
            depArrData = getLoadedFlightData(fileContent, "all")
            println(getLoadedFlightData(fileContent, "all").length)
            println(getLoadedFlightData(fileContent, "arr").length)
            println(getLoadedFlightData(fileContent, "dep").length)
            loaded = 1 // Update the loaded variable
            initialize() // Call initialize to update the visualization
          } catch {
            case ex: Exception => ex.printStackTrace() 
          }
        } 
      }
      liveItem.onAction = (e: ActionEvent) => {
        loaded = 0
        initialize()
      }
      
      if loaded == 0 then
        val timer = new Timer(true)
        val interval = 5.minute.toMillis

        timer.scheduleAtFixedRate(new TimerTask {
          def run(): Unit = {
            Platform.runLater(() => 
              if viewComboBox.value.value == "Graph View" then updateChart()
              else updateMetrics())
          }
        }, 0, interval)
      else updateChart()
        border.center = newCBoxes
        border.right = viewBoxHBox
        border.bottom = visible
        
      new VBox(border)
    }
      
    def initialize() = 
      val VBox1 = initializeGraph()
      val VBox2 = initializeGraph()
      val VBox3 = initializeGraph()
      val VBox4 = initializeGraph()

      if loaded == 0 then
        allTab.content = tables.createFlightTableAll()
        depTab.content = tables.createFlightTableDep()
        arrTab.content = tables.createFlightTableArr()
      else 
        allTab.content = createLoadedFlightTableAll()
        depTab.content = createLoadedFlightTableDep()
        arrTab.content = createLoadedFlightTableArr()
      stage.title = "Helsinki Airport Dashboard " + timeCalled


      val split = new SplitPane
      split.orientation = Orientation.Horizontal

      val split1 = new SplitPane
      split1.orientation = Orientation.Vertical
      split1.items.addAll(VBox1, VBox3)

      val split2 = new SplitPane
      split2.orientation = Orientation.Vertical
      split2.items.addAll(VBox2, VBox4)

      split.items.addAll(split1, split2, rectangleTool.makeRectangle(split))

      homeTab.content = split
      dataTab.content = tabPane1
      var isLightMode = true

      themeItem.onAction = (e: ActionEvent) => toggleTheme()

      def toggleTheme(): Unit = {
      if (isLightMode) {
        split1.background = darkBackgroundColor
        split2.background = darkBackgroundColor
        split.background = darkBackgroundColor
      } else {
        split1.background = lightBackgroundColor
        split2.background = lightBackgroundColor
        split.background = lightBackgroundColor 
      }
      isLightMode = !isLightMode

    }

      root.children += (menuBar, tabPane)
    initialize()


  end start

end Dashboard


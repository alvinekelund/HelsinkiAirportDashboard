file://<WORKSPACE>/src/main/scala/Dashboard.scala
### java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      =  extends JFXApp3.PrimaryStage {
  title = "Helsinki Airport Dashboard"
  width = 1500
  height = 970
  initStyle(StageStyle.DECORATED)
  vCURSORal darkBackgroundColor null
} # -1,
parent span = <1761..1965>,
child       = vCURSORal darkBackgroundColor null # -1,
child span  = [1912..1922..1968]

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.0
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-deQgC4rjSXyRVZtIonfQLQ== [exists ], <HOME>/Library/Caches/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.9.9/semanticdb-javac-0.9.9.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.0/scala3-library_3-3.3.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-core_3/0.14.2/circe-core_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-generic_3/0.14.2/circe-generic_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-parser_3/0.14.2/circe-parser_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scalafx/scalafx_3/20.0.0-R31/scalafx_3-20.0.0-R31.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http_3/10.5.3/akka-http_3-10.5.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client3/core_3/3.3.7/core_3-3.3.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client3/circe_3/3.3.7/circe_3-3.3.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_3/2.0.0/scala-xml_3-2.0.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-numbers_3/0.14.2/circe-numbers_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-core_3/2.7.0/cats-core_3-2.7.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-jawn_3/0.14.2/circe-jawn_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-base/20/javafx-base-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-controls/20/javafx-controls-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-fxml/20/javafx-fxml-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-graphics/20/javafx-graphics-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-media/20/javafx-media-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-swing/20/javafx-swing-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-web/20/javafx-web-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http-core_3/10.5.3/akka-http-core_3-10.5.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/model/core_3/1.4.7/core_3-1.4.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/shared/core_3/1.2.5/core_3-1.2.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/shared/ws_3/1.2.5/ws_3-1.2.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client3/json-common_3/3.3.7/json-common_3-3.3.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-kernel_3/2.7.0/cats-kernel_3-2.7.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/typelevel/simulacrum-scalafix-annotations_3/0.5.4/simulacrum-scalafix-annotations_3-0.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/typelevel/jawn-parser_3/1.3.2/jawn-parser_3-1.3.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-base/20/javafx-base-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-controls/20/javafx-controls-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-fxml/20/javafx-fxml-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-graphics/20/javafx-graphics-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-media/20/javafx-media-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-swing/20/javafx-swing-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-web/20/javafx-web-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-parsing_3/10.5.3/akka-parsing_3-10.5.3.jar [exists ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE>


action parameters:
offset: 1913
uri: file://<WORKSPACE>/src/main/scala/Dashboard.scala
text:
```scala
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
      height = 970
      initStyle(StageStyle.DECORATED)
      v@@al darkBackgroundColor = Color.rgb(34, 34, 34)
  val lightBackgroundColor = Color.rgb(240, 240, 240)

      val root = new Pane {
        background = darkBackgroundColor
      } 
    val scene = Scene(parent = root)
    stage.scene = scene

    val menuBar = new MenuBar
    val fileMenu = new Menu("File")
    val settingsMenu = new Menu("Settings")
    val saveItem = new MenuItem("Save")
    val openItem = new MenuItem("Open")
    val exitItem = new MenuItem("Exit")
    val liveItem = new MenuItem("Live")
    val themeItem = new MenuItem("Theme")
    fileMenu.items = List(liveItem, openItem, saveItem, new SeparatorMenuItem, exitItem)
    settingsMenu.items = List(themeItem)
    menuBar.menus = List(fileMenu, settingsMenu)
    menuBar.prefWidth = 1500

    openItem.accelerator = new KeyCodeCombination(KeyCode.O, KeyCombination.ControlDown)
    liveItem.accelerator = new KeyCodeCombination(KeyCode.L, KeyCombination.ControlDown)
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

    var fileContent: String = ""
    var loaded = 0
    

    
// general methods used in initializegraph
    def makeColumnGraph(graphDataType: Array[(String, Int)], x: String, y: String, label: String): BarChart[String, Number] =
      val columnChart = new ColumnChart()
      val chart = columnChart.createColumnChart(graphDataType, x, y, label)
      chart
    
    def makeScatterChart(graphDataType: Array[(String, Int)], x: String, y: String, label: String): ScatterChart[String, Number] = 
      val scatterChart = new ScatterPlot()
      val chart = scatterChart.createScatterChart(graphDataType, x, y, label)
      chart

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
    
    def makeDualChart(graphType: String, dataType: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String) = 
      graphType match
        case "Column" => makeTwoSeriesColumnChart(dataType, x, y, label1, label2)
        case "Scatter" => makeTwoSeriesScatterChart(dataType, x, y, label1, label2)
        case "Line" => makeTwoSeriesLineChart(dataType, x, y, label1, label2)
        case _ => throw new IllegalArgumentException("Invalid graph type")

    def createLoadedFlightTableAll(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent)
    }
    def createLoadedFlightTableDep(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent)
    }
    def createLoadedFlightTableArr(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent)
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
      
      var removeAdd = 1
      var parentPane: Pane = new Pane
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
      
      val datasetComboBoxM = new ComboBox(List("Total (24h)", "Busiest Hour", "Least Busy Hour"))
      datasetComboBoxM.value = "Total (24h)"

      val colorComboBox = new ComboBox(List("LightBlue", "Orange", "Green", "Yellow", "Grey"))
      colorComboBox.value = "LightBlue"

      val removeButton = new Button("Remove")
      val addButton = new Button("Add")
      val hideButton = new ToggleButton("Hide")
      val viewBoxHBox = new HBox(10, hideButton, viewComboBox)
      // initial box, changes instantly
      val comboBoxHBox1 = new HBox(10, graphComboBox, datasetComboBoxC, deparrComboBoxC1)  
      //val comboBoxHBox2 = new HBox(10, datasetComboBoxM, deparrComboBoxM, colorComboBox, removeButton, addButton)
           
      
      

      
      var changeInfo = 0
      var newCBoxes = comboBoxHBox1
      val border = new BorderPane
      border.center = newCBoxes
      border.right = viewBoxHBox
      border.bottom = visible
      def updateChart(): Unit = 
          
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
          
          if loaded == 1 then // Change the condition to check if the file is loaded
            depArrData = selectedDepArrC match 
              case "All" => getLoadedFlightData(fileContent) // Retrieve data from loaded file
              case "Departing" => getLoadedFlightData(fileContent)
              case "Arriving" => getLoadedFlightData(fileContent)
              case "Separate" => getLoadedFlightData(fileContent)
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
                                getChartData(selectedDatasetC, depArrData)._4))              
              else 
                new VBox(makeDualChart(selectedGraph, graphData.flightPerHourDepArr(getSepFlightData()._1, getSepFlightData()._2), "Time", "Airplane Flown", "Departures", "Arrivals"))

            else 
              new VBox(makeChart(selectedGraph, getChartData(selectedDatasetC, depArrData)._1, 
                getChartData(selectedDatasetC, depArrData)._2,
                getChartData(selectedDatasetC, depArrData)._3,
                getChartData(selectedDatasetC, depArrData)._4))
    
          newCBoxes.children.clear()
          if selectedDatasetC != "Time" || selectedGraph == "Pie" then
            c = 1
            newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC2)
          else 
            c = 0
            newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC1)
          visible = newChart
          border.center = newCBoxes
          border.right = viewBoxHBox
          border.bottom = visible

      def updateMetrics(): Unit =
            
          var selectedDatasetM = datasetComboBoxM.value.value
          var selectedDepArrM = deparrComboBoxM.value.value
          val selectedColor = colorComboBox.value.value

          if loaded == 1 then // Change the condition to check if the file is loaded
            depArrData = selectedDepArrM match 
              case "All" => getLoadedFlightData(fileContent) // Retrieve data from loaded file
              case "Departing" => getLoadedFlightData(fileContent)
              case "Arriving" => getLoadedFlightData(fileContent)
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
          newCBoxes.children.addAll(datasetComboBoxM, deparrComboBoxM, colorComboBox, removeButton, addButton)
          visible = newMetric
          border.center = newCBoxes
          border.right = viewBoxHBox
          border.bottom = visible

  
      
      graphComboBox.onAction = () => updateChart()
      datasetComboBoxC.onAction = () => updateChart()
      datasetComboBoxM.onAction = () => updateMetrics()
      deparrComboBoxM.onAction = () => updateMetrics()
      colorComboBox.onAction = () => updateMetrics()
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
            depArrData = getLoadedFlightData(fileContent)
            loaded = 1 // Update the loaded variable
            initialize() // Call updateChart to update the visualization
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
    initialize()

  end start

end Dashboard


```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:175)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.parsing.Parser.parse$$anonfun$1(ParserPhase.scala:38)
	dotty.tools.dotc.parsing.Parser.parse$$anonfun$adapted$1(ParserPhase.scala:39)
	scala.Function0.apply$mcV$sp(Function0.scala:42)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:437)
	dotty.tools.dotc.parsing.Parser.parse(ParserPhase.scala:39)
	dotty.tools.dotc.parsing.Parser.runOn$$anonfun$1(ParserPhase.scala:48)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.parsing.Parser.runOn(ParserPhase.scala:48)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:247)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1321)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:263)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:271)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:280)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:67)
	dotty.tools.dotc.Run.compileUnits(Run.scala:280)
	dotty.tools.dotc.Run.compileSources(Run.scala:195)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:165)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.completions.CompletionProvider.completions(CompletionProvider.scala:46)
	scala.meta.internal.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:146)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      =  extends JFXApp3.PrimaryStage {
  title = "Helsinki Airport Dashboard"
  width = 1500
  height = 970
  initStyle(StageStyle.DECORATED)
  vCURSORal darkBackgroundColor null
} # -1,
parent span = <1761..1965>,
child       = vCURSORal darkBackgroundColor null # -1,
child span  = [1912..1922..1968]
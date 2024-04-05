file://<WORKSPACE>/src/main/scala/Dashboard.scala
### java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      = new HBox(viewComboBox border null) # -1,
parent span = <9399..14188>,
child       = viewComboBox border null # -1,
child span  = [9409..9428..14194]

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.0
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-9cQVHeVlRgWgBif6k1-ilQ== [exists ], <HOME>/Library/Caches/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.9.9/semanticdb-javac-0.9.9.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.0/scala3-library_3-3.3.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-core_3/0.14.2/circe-core_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-generic_3/0.14.2/circe-generic_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-parser_3/0.14.2/circe-parser_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scalafx/scalafx_3/20.0.0-R31/scalafx_3-20.0.0-R31.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http_3/10.5.3/akka-http_3-10.5.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client3/core_3/3.3.7/core_3-3.3.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client3/circe_3/3.3.7/circe_3-3.3.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_3/2.0.0/scala-xml_3-2.0.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-numbers_3/0.14.2/circe-numbers_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-core_3/2.7.0/cats-core_3-2.7.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-jawn_3/0.14.2/circe-jawn_3-0.14.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-base/20/javafx-base-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-controls/20/javafx-controls-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-fxml/20/javafx-fxml-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-graphics/20/javafx-graphics-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-media/20/javafx-media-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-swing/20/javafx-swing-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-web/20/javafx-web-20.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http-core_3/10.5.3/akka-http-core_3-10.5.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/model/core_3/1.4.7/core_3-1.4.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/shared/core_3/1.2.5/core_3-1.2.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/shared/ws_3/1.2.5/ws_3-1.2.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client3/json-common_3/3.3.7/json-common_3-3.3.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-kernel_3/2.7.0/cats-kernel_3-2.7.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/typelevel/simulacrum-scalafix-annotations_3/0.5.4/simulacrum-scalafix-annotations_3-0.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/typelevel/jawn-parser_3/1.3.2/jawn-parser_3-1.3.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-base/20/javafx-base-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-controls/20/javafx-controls-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-fxml/20/javafx-fxml-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-graphics/20/javafx-graphics-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-media/20/javafx-media-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-swing/20/javafx-swing-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/openjfx/javafx-web/20/javafx-web-20-mac.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-parsing_3/10.5.3/akka-parsing_3-10.5.3.jar [exists ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE>


action parameters:
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

    val hideButton = new Button("Hide")
      hideButton.onAction = () => {
        // Toggle visibility of the object you want to hide
        // For example, if 'visible' is a VBox object, you can toggle its visibility as follows:
        visible.visible = !visible.visible.value
      }

    var isDarkMode = true

    themeItem.onAction = (e: ActionEvent) => toggleTheme()

    def toggleTheme(): Unit = {
    isDarkMode = !isDarkMode
    if (isDarkMode) {
      // Set dark mode styles
      stage.scene.value.stylesheets = List(getClass.getResource("/dark-theme.css").toExternalForm)
    } else {
      // Set light mode styles
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
    
    var visible: VBox = new VBox(0) 
    val metric = new Metric
    var removeAdd = 0
    var metricCount = 0

    var parentPane: Pane = new Pane

    def addMetric(card: StackPane): Pane = {
      if metricCount == 0 then 
        parentPane.getChildren().addAll(card)
        metricCount = 1
        parentPane
      else 
        parentPane
    }

    def removeMetric(): Pane = {
      parentPane.getChildren().clear()
      metricCount = 0
      parentPane
    }
    
        

    var chart: ScatterChart[String, Number] = makeScatterChart(getChartData("Time", depArrData)._1, "dfe", "fr", "rre")
    def initializeGraph(): VBox = {
      
      
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
    

      val comboBoxHBox1 = new HBox(10, graphComboBox, datasetComboBoxC, deparrComboBoxC)  
      val comboBoxHBox2 = new HBox(10, datasetComboBoxM, deparrComboBoxM, removeButton, addButton)
           
      
      chart.getData.foreach( series=> {
        series.getData.foreach( d => {
          val pointNode: scalafx.scene.Node = d.getNode
          val pointValue = d.getYValue.toString
          val pointTime = d.getXValue.toString
          val roundedValue = BigDecimal(pointValue).setScale(1, BigDecimal.RoundingMode.HALF_UP)
          val tooltip = new Tooltip()
          tooltip.setText(pointTime + ": " + "$" + roundedValue.toString)
          tooltip.setStyle("-fx-background-color: yellow; " + "-fx-text-fill: black; ")
          Tooltip.install(pointNode, tooltip)
        })})

      /*val total = Visual.PieGraph.getData.foldLeft(0.0) {(x, y) => x + y.getPieValue}
      Visual.PieGraph.getData.foreach( d => {
        val sliceNode: scalafx.scene.Node = d.getNode
        val pieValue = d.getPieValue
        val percent = (pieValue / total) * 100
        val msg = "%s: %.2f (%.2f%%)".format(d.getName, pieValue, percent)
        val tt = new Tooltip()
        tt.setText(msg)
        tt.setStyle("-fx-background-color: yellow; " +  "-fx-text-fill: black; ")
        Tooltip.install(sliceNode, tt) })
    */

      var newCBoxes = comboBoxHBox1
      var loaded = 0
      val border = new BorderPane
      border.center = newCBoxes
      border.right = new HBox (viewComboBox
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
          if selectedGraph == "Scatter" then
            val chart = makeChart(selectedGraph, getChartData(selectedDatasetC, depArrData)._1, 
                  getChartData(selectedDatasetC, depArrData)._2,
                  getChartData(selectedDatasetC, depArrData)._3,
                  getChartData(selectedDatasetC, depArrData)._4)
          newCBoxes.children.clear()
          newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC)
          visible = newChart
          border.center = newCBoxes
          border.right = viewComboBox
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

          if loaded == 0 then
            depArrData = selectedDepArrM match
              case "All" => getAllFlightData()
              case "Departing" => getDepFlightData()
              case "Arriving" => getArrFlightData()
          val newestMetric = metric.makeMetric(getMetricData(selectedDatasetM, depArrData)._2, getMetricData(selectedDatasetM, depArrData)._1)
          val newMetric =
              if removeAdd == 1 then 
                new VBox(addMetric(newestMetric))
              else
                new VBox(removeMetric())
          newCBoxes.children.clear()
          newCBoxes.children.addAll(datasetComboBoxM, deparrComboBoxM, removeButton, addButton)
          visible = newMetric
          border.center = newCBoxes
          border.right = viewComboBox
          border.bottom = visible


              
      graphComboBox.onAction = () => updateChart()
      datasetComboBoxC.onAction = () => updateChart()
      datasetComboBoxM.onAction = () => updateMetrics()
      deparrComboBoxC.onAction = () => updateChart()
      deparrComboBoxM.onAction = () => updateMetrics()
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
        border.right = viewComboBox
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
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:44)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:109)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      = new HBox(viewComboBox border null) # -1,
parent span = <9399..14188>,
child       = viewComboBox border null # -1,
child span  = [9409..9428..14194]
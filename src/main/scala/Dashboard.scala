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
    val colorItem = new MenuItem("Color")
    fileMenu.items = List(openItem, saveItem, new SeparatorMenuItem, exitItem)
    settingsMenu.items = List(colorItem)
    menuBar.menus = List(fileMenu, settingsMenu)
    menuBar.prefWidth = 1500

    openItem.accelerator = new KeyCodeCombination(KeyCode.O, KeyCombination.ControlDown)
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
    var newestMetric: StackPane = metric.makeMetric(getMetricData("Amount", depArrData)._2, getMetricData("Amount", depArrData)._1)

    var parentPane: Pane = new Pane
    parentPane.getChildren().addAll(metric.makeMetric(getMetricData("Amount", getAllFlightData())._2, getMetricData("Amount", getAllFlightData())._1), metric.makeMetric(getMetricData("Amount", getAllFlightData())._2, getMetricData("Amount", getAllFlightData())._1))

    def addMetric(card: StackPane): Pane = {
      if metricCount == 0 then 
        metric.metrics = card :: metric.metrics
        parentPane.getChildren().addAll(card)
        metricCount = 1
        parentPane
      else 
        parentPane
    }

    def removeMetric(): Pane = {
      metric.metrics = metric.metrics.filterNot(_ == card)
      parentPane.getChildren().clear()
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
      border.right = viewComboBox
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
        val newestMetric = metric.makeMetric(getMetricData("Amount", depArrData)._2, getMetricData("Amount", depArrData)._1)
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


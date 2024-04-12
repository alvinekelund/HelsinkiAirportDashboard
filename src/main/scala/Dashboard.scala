import Data.MetricData
import Data.GraphData
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.{KeyCode, KeyCodeCombination, KeyCombination}
import Data.DataParser.*
import scalafx.scene.control.*
import scalafx.Includes.*
import scalafx.event.ActionEvent
import javafx.scene.layout.VBox
import javafx.stage.StageStyle
import scalafx.collections.ObservableBuffer
import Data.Flight
import Visual.*
import Visual.LinePlot
import scalafx.scene.chart.*
import Visual.Tables
import Visual.ScatterPlot
import Data.DataParser
import javafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.geometry.Orientation
import scalafx.scene.control.ComboBox
import scalafx.scene.layout.StackPane
import java.util.Timer
import java.util.TimerTask
import scala.concurrent.duration.*
import scalafx.application.Platform
import javafx.scene.Node
import scalafx.stage.FileChooser

 object Dashboard extends JFXApp3:

  def start() =

    // Create the stage
    stage = new JFXApp3.PrimaryStage:
      title = ""
      width = 1500
      height = 970
      initStyle(StageStyle.DECORATED)
    val root = new Pane 
    val scene = Scene(parent = root)
    stage.scene = scene

    // Create the menubars and items
    val menuBar = new MenuBar
    val fileMenu = new Menu("File")
    val saveItem = new MenuItem("Save")
    val openItem = new MenuItem("Open")
    val exitItem = new MenuItem("Exit")
    val liveItem = new MenuItem("Live")
    fileMenu.items = List(liveItem, openItem, saveItem, new SeparatorMenuItem, exitItem)
    menuBar.menus = List(fileMenu)
    menuBar.prefWidth = 1500

// Short command accelerators
    openItem.accelerator = new KeyCodeCombination(KeyCode.O, KeyCombination.ControlDown)
    liveItem.accelerator = new KeyCodeCombination(KeyCode.L, KeyCombination.ControlDown)
    saveItem.accelerator = new KeyCodeCombination(KeyCode.S, KeyCombination.ControlDown)
    exitItem.accelerator = new KeyCodeCombination(KeyCode.X, KeyCombination.ControlDown)

// Create the tabpane for having a home tab and a data tab 
    val tabPane = new TabPane
    val homeTab = new Tab
    homeTab.text = "Home"
    val dataTab = new Tab
    dataTab.text = "Data"
    tabPane.layoutY = 30
    tabPane.minWidth = 1500
    tabPane.tabs = List(homeTab, dataTab)

    // inside of datatab, this adds three more tabs to select departing, arriving or all
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

    //creating a few variables
    var fileContent: String = ""
    var loadedData = false
    var depArrData = getAllFlightData()
    var initializeCounter = 0
    // creating instances of things needed for the creation of the ui
    val tables = new Tables
    val graphData = new GraphData
    val metricData = new MetricData

// creates a chart based on the parameters which we asses when updateChart is used
    def makeChart(graphType: String, dataType: Array[(String, Int)], x: String, y: String, label: String, color: String): Chart = 
      graphType match
        case "Column" => 
          val columnChart = new ColumnChart()
          columnChart.createColumnChart(dataType, x, y, label, color)
        case "Scatter" =>
          val scatterChart = new ScatterPlot()
          scatterChart.createScatterChart(dataType, x, y, label, color)
        case "Line" => 
          val lineChart = new LinePlot()
          lineChart.createLineChart(dataType, x, y, label, color)
        case "Pie" => 
          val pieChart = new PieGraph()
          pieChart.createPieChart(dataType, x, y, label, color)
        case _ => throw new IllegalArgumentException("Invalid graph type")
    
    // creates a dual series chart based on the parameters which we asses when updateChart is used
    def makeDualChart(graphType: String, dataType: Array[(String, Int, Int)], x: String, y: String, label1: String, label2: String): XYChart[String, Number] = 
      graphType match
        case "Column" => 
          val columnChart = new ColumnChart()
          columnChart.createColumnChartTwoSeries(dataType, x, y, label1, label2)
        case "Scatter" => 
          val scatterChart = new ScatterPlot()
          scatterChart.createScatterChartTwoSeries(dataType, x, y, label1, label2)
        case "Line" => 
          val lineChart = new LinePlot()
          lineChart.createLineChartTwoSeries(dataType, x, y, label1, label2)
        case _ => throw new IllegalArgumentException("Invalid graph type")
      
    //Creates tables for loaded flights, it's here since filecontent is determined here. 
    def createLoadedFlightTableAll(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent, "all")
    }
    def createLoadedFlightTableDep(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent, "dep")
    }
    def createLoadedFlightTableArr(): TableView[Flight] = {
        tables.createLoadedFlightTable(fileContent, "arr")
    }

    // this determines the names of the axies and which data to look at, together with makechart these create everything needed for a chart
    def getChartData(dataset: String, depArr: ObservableBuffer[Flight]): Tuple4[Array[(String, Int)], String, String, String] = dataset match {
            case "Time" => Tuple4(graphData.flightPerHourData(depArr), "Time", "Flights", "Airplanes flown each hour")
            case "Carrier" => Tuple4(graphData.flightPerCarrierData(depArr), "Carrier", "Flights", "Planes flown by carrier")
            case "Model" => Tuple4(graphData.flightPerModelData(depArr), "Airplane Model", "Flights", "Planes flown by model")
            case _ => throw new IllegalArgumentException("Invalid dataset")
          }
    // this determines the values of the card and which data to look at, together with makemetruc these create everything needed for a metric card
    def getMetricData(dataset: String, depArr: ObservableBuffer[Flight]): Tuple2[String, String] = dataset match {
            case "Total (24h)" => Tuple2(metricData.totalFlights(depArr), "Total (24h)")
            case "Busiest Hour" => Tuple2(metricData.busiestHour(depArr), "Busiest Hour")
            case "Least Busy Hour" => Tuple2(metricData.leastBusyHour(depArr), "Least Busy Hour")
            case "Most Common Carrier" => Tuple2(metricData.mostCommonCarrier(depArr), "Most Common Carrier")
            case "Most Common Model" => Tuple2(metricData.mostCommonModel(depArr), "Most Common Model")
            case _ => throw new IllegalArgumentException("Invalid dataset")
          }
    
    // initializes the graph/metric window
    def initializeGraph(): VBox =  
      // Create separate instences of these for each pane
      val metric = new Metric
      val border = new BorderPane
      val rectangleTool = new RectangleTool
      var removeAdd = true
      var boxC1 = true
            
      var visible: VBox = new VBox(0)    
      val parentPane: Pane = new Pane
      val rectangle = rectangleTool.makeRectangle(parentPane)
      parentPane.getChildren.addAll(rectangle)    

      // adds a metric card 
      def addMetric(card: StackPane): Pane = {
        parentPane.getChildren().clear()
        parentPane.getChildren().addAll(card, rectangle)
        parentPane

      }
// removes a metric card
      def removeMetric(): Pane = 
        metric.selectedCard match
          case None => parentPane
          case Some(card) =>
            parentPane.getChildren().clear()
            parentPane.getChildren().addAll(rectangle)
            parentPane
  

      // All different instances for the comboboxes. 
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
      val datasetComboBoxC = new ComboBox(List("Carrier", "Time", "Model"))
      datasetComboBoxC.value = "Time"
      val colorComboBoxC = new ComboBox(List("LightBlue", "Orange", "Green", "Blue", "Red", "Grey"))
      colorComboBoxC.value = "Orange"
      val datasetComboBoxM = new ComboBox(List("Total (24h)", "Busiest Hour", "Least Busy Hour", "Most Common Carrier", "Most Common Model"))
      datasetComboBoxM.value = "Total (24h)"
      val colorComboBoxM = new ComboBox(List("LightBlue", "Orange", "Green", "Blue", "Red", "Grey"))
      colorComboBoxM.value = "LightBlue"  
      
      // initial box, changes instantly
      val comboBoxHBox1 = new HBox(10, graphComboBox, datasetComboBoxC, deparrComboBoxC1, colorComboBoxC) 
      var newCBoxes = comboBoxHBox1

      // buttons for the metrics
      val removeButton = new Button("Remove")
      val addButton = new Button("Add")
      val hideButton = new ToggleButton("Hide")
      val viewBoxHBox = new HBox(10, hideButton, viewComboBox)

      // match statement which adds the offset for the specific pane. 
      initializeCounter match
        case _ if initializeCounter % 4 == 0 =>
          rectangleTool.offsetX = 0.0
          rectangleTool.offsetY = 88.0
        case _ if initializeCounter % 4 == 1 =>
          rectangleTool.offsetX = 755.0
          rectangleTool.offsetY = 88.0
        case _ if initializeCounter % 4 == 2 =>
          rectangleTool.offsetX = 0.0
          rectangleTool.offsetY = 530.0
        case _ if initializeCounter % 4 == 3 =>
          rectangleTool.offsetX = 755.0
          rectangleTool.offsetY = 530.0
      initializeCounter += 1

      // preniminaly layout
      border.center = newCBoxes
      border.right = viewBoxHBox
      border.bottom = visible

      // method for updating chart based on values in comboboxes
      def updateChart(): Unit = 
          stage.title = "Helsinki Airport Dashboard " + timeCalled
          var selectedDepArrC = 
            if boxC1 && deparrComboBoxC1.value.value != "Separate" then 
              deparrComboBoxC2.value.value = deparrComboBoxC1.value.value
              deparrComboBoxC1.value.value 
            else if !boxC1 then
              deparrComboBoxC1.value.value = deparrComboBoxC2.value.value
              deparrComboBoxC2.value.value 
            else
              deparrComboBoxC1.value.value
          var selectedDatasetC = datasetComboBoxC.value.value
          var selectedGraph = graphComboBox.value.value
          var selectedColorC = colorComboBoxC.value.value
          
          if loadedData then // Checks wether the file is loaded
            depArrData = selectedDepArrC match 
              case "All" => getLoadedFlightData(fileContent, "all")
              case "Departing" => getLoadedFlightData(fileContent, "dep")
              case "Arriving" => getLoadedFlightData(fileContent, "arr")
              case _ => getLoadedFlightData(fileContent, "all")
          else 
            depArrData = selectedDepArrC match
              case "All" => getAllFlightData() // Retrieve data from default methods
              case "Departing" => getDepFlightData()
              case "Arriving" => getArrFlightData()
              case _ => getAllFlightData()
          // creates the new chart based on what the parameters are.
          val newChart = 
            if selectedDepArrC == "Separate" then
              if selectedDatasetC != "Time" || selectedGraph == "Pie" then
                new VBox(makeChart(selectedGraph, getChartData(selectedDatasetC, depArrData)._1, 
                                getChartData(selectedDatasetC, depArrData)._2,
                                getChartData(selectedDatasetC, depArrData)._3,
                                getChartData(selectedDatasetC, depArrData)._4, 
                                selectedColorC))              
              else 
                if !loadedData then
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

// updates the comboboxes to the right state  based on what the values in the comboboxes are
          if selectedGraph == "Pie" || (selectedDatasetC != "Time" && selectedDepArrC == "Separate") then
            boxC1 = false
            newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC2)
          else if selectedDatasetC != "Time" then
            boxC1 = false
            newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC2, colorComboBoxC)
          else if selectedDepArrC == "Separate" then
            boxC1 = true
            newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC1)
          else 
            boxC1 = true
            newCBoxes.children.addAll(graphComboBox, datasetComboBoxC, deparrComboBoxC1, colorComboBoxC)
          visible = newChart
          border.center = newCBoxes
          border.right = viewBoxHBox
          border.bottom = visible

          // updates metric 
      def updateMetrics(): Unit =

          stage.title = "Helsinki Airport Dashboard " + timeCalled
            
          var selectedDatasetM = datasetComboBoxM.value.value
          var selectedDepArrM = deparrComboBoxM.value.value
          val selectedColor = colorComboBoxM.value.value

          if loadedData then // Checks if the data is loaded
            depArrData = selectedDepArrM match 
              case "All" => getLoadedFlightData(fileContent, "all")
              case "Departing" => getLoadedFlightData(fileContent, "dep")
              case "Arriving" => getLoadedFlightData(fileContent, "arr")
          else 
            depArrData = selectedDepArrM match
              case "All" => getAllFlightData() 
              case "Departing" => getDepFlightData()
              case "Arriving" => getArrFlightData()

// the new metric to be added
          val newestMetric = metric.makeMetric(getMetricData(selectedDatasetM, depArrData)._2, getMetricData(selectedDatasetM, depArrData)._1, selectedColor)
// adds or removes a metric based on what is pressed
          val newMetric =
              if !removeAdd then 
                removeAdd = true
                new VBox(removeMetric())
              else
                new VBox(addMetric(newestMetric))
// updates comboboxes
          newCBoxes.children.clear()
          newCBoxes.children.addAll(datasetComboBoxM, deparrComboBoxM, colorComboBoxM, removeButton, addButton)
          visible = newMetric
          // changes the pane accordingly
          border.center = newCBoxes
          border.right = viewBoxHBox
          border.bottom = visible

  
      // onAction logic. 
      graphComboBox.onAction = () => updateChart()
      datasetComboBoxC.onAction = () => updateChart()
      datasetComboBoxM.onAction = () => updateMetrics()
      deparrComboBoxM.onAction = () => updateMetrics()
      colorComboBoxM.onAction = () => updateMetrics()
      colorComboBoxC.onAction = () => updateChart()
      deparrComboBoxC1.onAction = () => 
        boxC1 = true
        updateChart()
      deparrComboBoxC2.onAction = () => 
        boxC1 = false
        updateChart()
      viewComboBox.onAction = () => 
        if (viewComboBox.value.value == "Graph View") then updateChart()
        else updateMetrics()
      removeButton.onAction = (e: ActionEvent) => {
        removeAdd = false
        updateMetrics()
      }
      addButton.onAction = (e: ActionEvent) => {
        updateMetrics()
      }
      // hides the item
      hideButton.onAction = () => {
        visible.visible = !visible.visible.value
      }
      exitItem.onAction = (e: ActionEvent) => sys.exit(0)
      
      // writes the xml file to be saved and saves it to computer
      saveItem.onAction = (e: ActionEvent) => {
      val fileChooser = new FileChooser
      val selectedFile = fileChooser.showSaveDialog(stage)
      if (selectedFile != null) {
      val writer = new java.io.PrintWriter(selectedFile)
      writer.write(xmlFlightData())
      writer.close()
        }
      }
      // trys to open up new item, if succesfull it calls initalize.
      openItem.onAction = (e: ActionEvent) => {
        val fileChooser = new FileChooser
        val selectedFile = fileChooser.showOpenDialog(stage)
        if (selectedFile != null) {
          try {
            fileContent = scala.io.Source.fromFile(selectedFile).mkString
            depArrData = getLoadedFlightData(fileContent, "all")
            loadedData = true
            initialize()
          } catch {
            case ex: Exception => ex.printStackTrace() 
          }
        } 
      }
      liveItem.onAction = (e: ActionEvent) => {
        loadedData = false
        initialize()
      }
      // Updates live items every 5 mins
      if !loadedData then
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
      new VBox(border)
    
    // initializes a new window when started, item is opened or live is pressed.
    def initialize(): Unit = 
      // calls initializegrapgh four separate times.
      val VBox1 = initializeGraph()
      val VBox2 = initializeGraph()
      val VBox3 = initializeGraph()
      val VBox4 = initializeGraph()

      // select tab content for the data tab
      if !loadedData then
        allTab.content = tables.createFlightTableAll()
        depTab.content = tables.createFlightTableDep()
        arrTab.content = tables.createFlightTableArr()
      else 
        allTab.content = createLoadedFlightTableAll()
        depTab.content = createLoadedFlightTableDep()
        arrTab.content = createLoadedFlightTableArr()
      stage.title = "Helsinki Airport Dashboard " + timeCalled

      // put these four boxes in a four-window splitpane.
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


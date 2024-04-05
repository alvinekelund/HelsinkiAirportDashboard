package Visual
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
import Visual.GraphData
import Visual.ColumnChart
import scalafx.scene.chart._
import java.util.Locale.Category
import scalafx.stage.FileChooser
import cats.conversions.all

class Tables {


    def createFlightTable(set: String): TableView[Flight] = {
        val tableView = new TableView(getFlightData(set))
        tableView.minHeight = 850
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

        tableView
    }

    def createFlightTableAll(): TableView[Flight] = {
        createFlightTable("all")
    }
    def createFlightTableDep(): TableView[Flight] = {
        createFlightTable("dep")
    }
    def createFlightTableArr(): TableView[Flight] = {
        createFlightTable("arr")
    }
}

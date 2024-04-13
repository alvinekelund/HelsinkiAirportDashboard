package Visual

import Data.DataParser.*
import scalafx.scene.control._
import scalafx.beans.property.StringProperty
import Data.Flight

class Tables {

// Creates the table for data tab from live call
    def createFlightTable(set: String): TableView[Flight] = {
        val tableView = new TableView(getFlightData(set))
        tableView.minHeight = 850
        val col1 = new TableColumn[Flight, String]("Flight Number")
        col1.cellValueFactory = cdf => StringProperty(cdf.value.fltnr)
        val col2 = new TableColumn[Flight, String]("Time of dep/arr (GMT):")
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
        val col9 = new TableColumn[Flight, String]("Callsign")
        col9.cellValueFactory = cdf => StringProperty(cdf.value.callsign)
        val col10 = new TableColumn[Flight, String]("Blt Area")
        col10.cellValueFactory = cdf => StringProperty(cdf.value.bltarea)
        

        tableView.columns ++= List(col1, col2, col3, col4, col5, col6, col7, col9, col10)

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
// creates the table for data tab from loaded file.
    def createLoadedFlightTable(loadedData: String, set: String): TableView[Flight] = {
        val tableView = new TableView(getLoadedFlightData(loadedData, set))
        tableView.minHeight = 850
        val col1 = new TableColumn[Flight, String]("Flight Number")
        col1.cellValueFactory = cdf => StringProperty(cdf.value.fltnr)
        val col2 = new TableColumn[Flight, String]("Time of dep/arr (GMT):e")
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
        val col9 = new TableColumn[Flight, String]("Callsign")
        col9.cellValueFactory = cdf => StringProperty(cdf.value.callsign)
        val col10 = new TableColumn[Flight, String]("Blt Area")
        col10.cellValueFactory = cdf => StringProperty(cdf.value.bltarea)
        

        tableView.columns ++= List(col1, col2, col3, col4, col5, col6, col7, col9, col10)

        tableView
    }
    
}

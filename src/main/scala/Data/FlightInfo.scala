package finaviaAPI

import scala.xml.Node

case class Flight(
  fltnr: String,
  h_apt: String,
  sdt: String,
  sdate: String,
  acreg: String,
  actype: String,
  mfltnr: String,
  route_1: String,
  route_n_1: String,
  callsign: String,
  bltarea: String
)

object Flight {
  def fromXml(node: Node): Flight = {
    Flight(
      (node \ "fltnr").text,
      (node \ "h_apt").text,
      (node \ "sdt").text,
      (node \ "sdate").text,
      (node \ "acreg").text,
      (node \ "actype").text,
      (node \ "mfltnr").text,
      (node \ "route_1").text,
      (node \ "route_n_1").text,
      (node \ "callsign").text, 
      (node \ "bltarea").text
    )
  }
}


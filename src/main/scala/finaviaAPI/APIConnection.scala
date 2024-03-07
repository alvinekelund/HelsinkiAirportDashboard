package finaviaAPI

import scala.io.Source
import scala.util.{Failure, Success, Using}
import java.io.PrintWriter

import io.circe.parser.decode
import io.circe.*, io.circe.parser.*, io.circe.syntax.*, io.circe.generic.auto.*

import scala.io.Source
import scala.util.{Success, Failure, Using, Try}

import sttp.client3._
import sttp.client3.circe._
import io.circe.generic.auto._

object APIConnection:

  /**
   * Fetches and provides the API key which is used by Forecast, CurrentWeather and AirPollution
   */
  lazy val apiKey: String = {
    val apiKeyFile = "0f1e817b"
    val source = Source.fromFile(apiKeyFile)
    val key = source.getLines().mkString
    source.close()
    key
  }
  private var callTimes = 0

  def apiCallCounter() =
    callTimes += 1
    if callTimes > 1000 then
      throw new Exception("Too many calls to API")

  def fetch(url: String): String =
    apiCallCounter()
    val response = Using(Source.fromURL(url)){
      source => source.getLines().mkString
  }
    response match
        case Success(data) => data
        case Failure(e)    =>
          Console.err.println("Connection failed")
          throw e



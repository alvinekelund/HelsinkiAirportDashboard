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

  private var callTimes = 0

  def apiCallCounter() =
    callTimes += 1
    if callTimes > 1000 then
      throw new Exception("Too many calls to API")


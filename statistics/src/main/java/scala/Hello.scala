//import scala.util.parsing.json._

import io.circe.parser.decode

import java.util.Scanner
import scala.io.Source
import scala.collection.immutable
import scala.List
import java.io.File

private object Hello {
  def main(args: Array[String]): Unit = {
    //println("ciao")
    //val intsJson = List("luca", "2", "3").asJson
    //println(intsJson)
    //val out = intsJson.as[List[String]]
    //val out=decode[List[Int]]("[ 1    , 2    ,        3]")
    //val json_content = scala.io.Source.fromFile("file.json").mkString
    //val json_data = JSON.parseFull(json_content)
    val filename = "file.txt";
    var rawJson = ""
    var l: List[Detection] = Nil
    val file = new File(filename)
    val reader = new Scanner(file)
    val TIME = 100
    val SLEEPING_TIME = 20000

    while (reader.hasNextLine) {
      rawJson = reader.nextLine()
      //println(rawJson);
      val out = decode[List[String]](rawJson)

      println(out)
      out match {
        case Left(value) => println("ERRORE")
        case Right(value) => {
          l = Support.detectionFromString(value) :: l
        }
      }

      if(l.length >= TIME){
        Support.executeQueries(l)
        l = Nil
      }

      while(!reader.hasNextLine){
        Thread.sleep(SLEEPING_TIME)
      }
    }


  }

}

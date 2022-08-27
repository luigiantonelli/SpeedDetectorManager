//import scala.util.parsing.json._

import io.circe.parser.decode

import scala.io.Source

private object Hello {
  def main(args: Array[String]): Unit = {
    println("ciao")
    //val intsJson = List("luca", "2", "3").asJson
    //println(intsJson)
    //val out = intsJson.as[List[String]]
    //val out=decode[List[Int]]("[ 1    , 2    ,        3]")
    //val json_content = scala.io.Source.fromFile("file.json").mkString
    //val json_data = JSON.parseFull(json_content)
    val filename = "file.txt";
    var rawJson = ""
    var l: List[Detection] = Nil
    for (line <- Source.fromFile(filename).getLines) {
      //rawJson = rawJson+line+"\n"
      rawJson = line
      println(rawJson);
      val out = decode[List[String]](rawJson)

      println(out)
      out match {
        case Left(value) => println("ERRORE")
        case Right(value) => {
          l = Support.detectionFromString(value) :: l
        }
      }

    }
    println(Utils.mostActiveSpeedCamera(l))
    println(Utils.highestSpeed(l))
    println(Utils.busiestRegion(l))
    println(Utils.ecoFriendlyPercentage(l))
    println(Utils.mostCriminalRegion(l))
    println(Utils.highestAverageSpeedCamera(l))
    println(Utils.highestAverageRegion(l))
    println(Utils.mostUsedFuel(l))
  }
  /*val l = List(new Detection(1,1,120,"ABC",CarType.SUV,FuelType.HYBRID,"Lazio",RoadType.HIGHWAY, false),
    new Detection(2,2,120,"ABC",CarType.SUV,FuelType.GASOLINE,"Lazio",RoadType.HIGHWAY, false),
    new Detection(3,3,140,"ABC",CarType.SUV,FuelType.DIESEL,"Molise",RoadType.HIGHWAY, true),
    new Detection(3,3,70,"ABC",CarType.SUV,FuelType.DIESEL,"Molise",RoadType.HIGHWAY, false),
    new Detection(4,1,30,"ABC",CarType.SUV,FuelType.HYBRID,"Lazio",RoadType.HIGHWAY, false))*/


  /*
      val parseResult = parse(rawJson)
      parseResult match {
        case Right(decodedJson) =>
          val jsonObject: Json = decodedJson.asJson


          decode[Detection](jsonObject.spaces2)
      }*/


}

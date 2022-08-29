package scala

import it.uniroma1.commons.queue.enums.{CarType, FuelType, RoadType}
import scala.collection.immutable
import scala.List
object Support {
  def detectionFromString(v: List[String]): Detection = {
    var ct = CarType.SUV
    if (v(4).equals("MICRO"))
      ct = CarType.MICRO
    else if (v(4).equals("VAN"))
      ct = CarType.VAN
    else if (v(4).equals("COUPE"))
      ct = CarType.COUPE
    else if (v(4).equals("SUPERCAR"))
      ct = CarType.SUPERCAR
    else if (v(4).equals("CAMPER"))
      ct = CarType.CAMPER
    else if (v(4).equals("TRUCK"))
      ct = CarType.TRUCK

    var ft = FuelType.GASOLINE
    if (v(5).equals("DIESEL"))
      ft = FuelType.DIESEL
    else if (v(5).equals("LPG"))
      ft = FuelType.LPG
    else if (v(5).equals("ETHANOL"))
      ft = FuelType.ETHANOL
    else if (v(5).equals("HYBRID"))
      ft = FuelType.HYBRID
    else if (v(5).equals("FULL_ELECTRIC"))
      ft = FuelType.FULL_ELECTRIC

    var rt = RoadType.CITY_ROAD
    if (v(7).equals("HIGHWAY"))
      rt = RoadType.HIGHWAY
    else if (v(7).equals("THROUGHWAY"))
      rt = RoadType.THROUGHWAY

    new Detection(v(0).toLong, v(1).toLong, v(2).toInt, v(3), ct, ft, v(6), rt, v(8).toBoolean)
  }

}

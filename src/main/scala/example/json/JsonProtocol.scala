package example.json

import spray.json._

import example.{Bar, Baz, Foo, Qux}
import scala.reflect.runtime.universe._

trait JsonProtocol {
  import DefaultJsonProtocol._

  def stringFormat[T: TypeTag](deserializer: String => T, serializer: T => String): RootJsonFormat[T] = new RootJsonFormat[T] {

    override def write(value: T): JsValue = JsString(serializer(value))

    override def read(json: JsValue): T = json match {
      case JsString(value) => deserializer(value)
      case _ => deserializationError(s"foo")
//      case _ => deserializationError(s"${typeOf[T]} expected")
    }
  }

  implicit val fooFormatHighPrio = stringFormat[Foo](x => Foo(x.toInt + 1), _.a.toString)
  implicit val barFormat = stringFormat[Bar](x => Bar(x.toInt), _.b.toString)

  //THIS VERSION COMPILES
//  implicit val quxFormat = stringFormat[Qux](x => Qux(x.toInt), _.c.toString)
//  implicit val bazFormat = jsonFormat2(Baz)

  //THIS VERSION DOES NOT COMPILE
    implicit val bazFormat = jsonFormat2(Baz)
    implicit val quxFormat = stringFormat[Qux](x => Qux(x.toInt), _.c.toString)
}

object JsonProtocol extends DefaultJsonProtocol with JsonProtocol

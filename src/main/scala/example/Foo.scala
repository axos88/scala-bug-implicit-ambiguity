package example

import example.json.JsonProtocol._
import spray.json._

case class Foo(a: Int)
case class Bar(b: Int)
case class Baz(f: Foo, b: Bar)
case class Qux(c: Int)

trait FooLowestPrioImplicits {
  implicit val fooFormatLowestPrio = stringFormat[Foo](x => Foo(x.toInt - 1), _.a.toString)
}

object Foo extends  FooLowestPrioImplicits {
  implicit val fooFormatLowPrio = stringFormat[Foo](x => Foo(x.toInt), _.a.toString)
}

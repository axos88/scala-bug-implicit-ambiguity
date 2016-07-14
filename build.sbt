import sbt._
import Keys._

name := "implicit-bug"

organization := "scala.bugs"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "io.spray"              %% "spray-json"             % "1.3.2",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)


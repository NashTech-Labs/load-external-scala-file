package com.rishi

object Boot extends App {
  val result = new ExternalScalaFileHelper().processExternalFile("/home/knol2015/Application/blog/ExternalFile.scala", Input(10, 20))
  println(result)
}

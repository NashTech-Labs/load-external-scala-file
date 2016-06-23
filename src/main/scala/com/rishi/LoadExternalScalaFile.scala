package com.rishi


import java.io.File

import scala.io.Source
import scala.reflect.runtime.currentMirror
import scala.tools.reflect.ToolBox

abstract class ExteranlProcessing {
  def process(a: Int, b: Int): Int
}

case class Input(a: Int, b: Int)

case class LoadExternalScalaFile(filePath: String) {
  val toolbox = currentMirror.mkToolBox()
  val fileContents = Source.fromFile(filePath).getLines().mkString("\n")
  val tree = toolbox.parse(s"import com.rishi._;$fileContents")
  val compiledCode = toolbox.compile(tree)

  def getFileReference: ExteranlProcessing = compiledCode().asInstanceOf[ExteranlProcessing]
}

class ExternalScalaFileHelper {
  def processExternalFile(filePath: String, data: Input): Either[String, Int] = {
    try {
      if (new File(filePath).exists()) {
        val externalFile = LoadExternalScalaFile(filePath)
        val externalProcessing = externalFile.getFileReference
        Right(externalProcessing.process(data.a, data.b))
      } else Left("Incorrect file path")
    } catch {
      case ex: Exception => Left(ex.getMessage)
    }
  }
}

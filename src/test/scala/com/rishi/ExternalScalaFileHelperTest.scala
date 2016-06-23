package com.rishi

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import org.scalatest.{FlatSpec, Matchers}

class ExternalScalaFileHelperTest extends FlatSpec with Matchers {

  val externalScalaFileHelper = new ExternalScalaFileHelper

  "External Scala File helper" should "not be able to process external file when invalid file path provided" in {
    externalScalaFileHelper.processExternalFile("invalid path", Input(3, 4)).shouldBe(Left("Incorrect file path"))
  }

  it should "not be able to process external file when there is some error" in {
    val fileContent =
      """new ExteranlProcessing {
        |  override def process(a:Int,b:Int) = divide(a,b)
        |   def divide(a:Int,b:Int) = a / b
        |}
      """.stripMargin
    val filePath = System.getProperty("java.io.tmpdir") + "/ExternalFile"
    Files.write(Paths.get(filePath), fileContent.getBytes(StandardCharsets.UTF_8))
    val result = externalScalaFileHelper.processExternalFile(filePath, Input(3, 0))
    new File(filePath).delete()
    result.isLeft
  }

  it should "be able to process external file" in {
    val fileContent =
      """new ExteranlProcessing {
        |  override def process(a:Int,b:Int) = sum(a,b)
        |   def sum(a:Int,b:Int) = a + b
        |}
      """.stripMargin
    val filePath = System.getProperty("java.io.tmpdir") + "/ExternalFile"
    Files.write(Paths.get(filePath), fileContent.getBytes(StandardCharsets.UTF_8))
    val result = externalScalaFileHelper.processExternalFile(filePath, Input(12, 15))
    new File(filePath).delete()
    result.shouldBe(Right(27))
  }
}


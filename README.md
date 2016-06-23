# load-external-scala-file
This application will teach how to compile and load an external scala file in a scala apllication

### Run application
sbt run

### Run test cases
sbt test

### External file content (ExternalFile.scala)
<pre>
new ExternalProcessing {
  override def process(a:Int,b:Int) = a + b
}
</pre>

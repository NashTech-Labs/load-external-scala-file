# load-external-scala-file
This application will teach how to compile and load an external scala file in a scala apllication

### Run application
sbt run

### Run test cases
sbt test

### External file content (ExternalFile.scala)
<pre>
new ExteranlProcessing {
  override def process(a:Int,b:Int) = sum(a,b)
   def sum(a:Int,b:Int) = a + b
}
</pre>

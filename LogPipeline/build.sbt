name := "LogPipeline"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.apache.kafka" %% "kafka" % "0.8.2.1",
  "org.apache.spark" %% "spark-core" % "1.4.0",
  "org.apache.spark" %% "spark-streaming" % "1.4.0",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.4.0"
)
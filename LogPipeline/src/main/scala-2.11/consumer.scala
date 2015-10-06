import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Time
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.slf4j.LoggerFactory
import kafka.serializer.StringDecoder

object SparkStreamingConsumer {

  def main(args: Array[String]) {

    // spark streaming setup
    val conf = new SparkConf()
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(1))

    // Kafka setup
    val kafkaBrokers = sys.env("CONSUMER_KAFKA_BROKERS")
    val kafkaTopics = sys.env("CONSUMER_KAFKA_TOPIC").split(",").toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaBrokers)

    // routing
    val stream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, kafkaTopics)

    // processing
    /*
    stream.foreachRDD {
    		//println(rdd.toString())
    }
    *
    */

    ssc.start()
    ssc.awaitTermination()
  }
}
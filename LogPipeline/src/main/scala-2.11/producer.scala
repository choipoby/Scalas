/**
 * Created by schoi on 10/5/15.
 */
import java.util.Properties
import kafka.producer.{ KeyedMessage, ProducerConfig, Producer => KafkaProducer }

case class Producer[A]() {
  val props = new Properties()
  val kafkaBrokers = sys.env("PRODUCER_KAFKA_BROKERS")
  val kafkaTopic = sys.env("PRODUCER_KAFKA_TOPIC")
  props.put("metadata.broker.list", kafkaBrokers)
  props.put("partitioner.class", "kafka.producer.DefaultPartitioner")
  props.put("serializer.class", "kafka.serializer.StringEncoder");

  val config = new ProducerConfig(props)
  private lazy val producer = new KafkaProducer[A, A](config)

  def send(message: A) = sendMessage(producer, keyedMessage(kafkaTopic, message))

  def sendStream(stream: Stream[A]) = {
    val iter = stream.iterator
    while (iter.hasNext) {
      send(iter.next())
    }
  }

  private def keyedMessage(topic: String, message: A): KeyedMessage[A, A] = new KeyedMessage[A, A](topic, message)
  private def sendMessage(producer: KafkaProducer[A, A], message: KeyedMessage[A, A]) = producer.send(message)
}

object Producer {
  def apply[T](topic: String, props: Properties) = new Producer[T]() {
  }
}
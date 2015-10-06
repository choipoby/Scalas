object Main {
  def main(args: Array[String]): Unit = {
    val strProducer = Producer[String]()
    for (ln <- io.Source.stdin.getLines) strProducer.send(ln)
  }
}

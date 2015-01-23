package services

import java.util.Properties

import akka.actor.{Props, Actor}
import kafka.consumer.{KafkaStream, Consumer, ConsumerConfig}
import play.Logger

object KafkaConsumer {
  def props(topic: String): Props = Props(new KafkaConsumer(topic))
}

class KafkaConsumer(topic: String) extends Actor {
  val props = new Properties()
  props.put("auto.commit", "true")
  props.put("zookeeper.connect", "localhost:2181")

  def receive = {
    case group: String => {
      props.put("group.id", group)

      val connector = Consumer.create(new ConsumerConfig(props))
      val stream: KafkaStream[Array[Byte], Array[Byte]] = connector.createMessageStreams(Map(topic -> 1)).get(topic).get(0)
      try {
        for (message <- stream) {
          try {
            Logger.debug(new String(message.message()))
          } catch {
            case e: Throwable => Logger.error("error processing message, skipping and resume consumption: " + e)
          }
        }
      }
    }
  }
}
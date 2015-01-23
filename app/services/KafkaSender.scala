package services

import akka.actor.{Props, Actor}
import play.Logger

object KafkaSender {
  def props(topic: String): Props = Props(new KafkaSender(topic))
}

class KafkaSender(topic: String) extends Actor {
  // broker is running by default on host 9092
  val producer = new KafkaProducer(topic,"localhost:9092")

  def receive = {
    case message: String => {
      try {
        producer.send(message)
      }
      catch {
        case e: Exception => Logger.error("Could not send message.", e)
      }
    }
  }
}

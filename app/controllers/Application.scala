package controllers

import akka.actor.{ActorSystem, ActorRef}
import play.api.libs.json._
import play.api.mvc._
import models.Book._
import services.{KafkaConsumer, KafkaSender}

object Application extends Controller {

  val system = ActorSystem("KafkaClients")

  // make a producer and consumer for the "myevents" topic
  val kafkaProducer: ActorRef = system.actorOf(KafkaSender.props("myevents"), "producertest")
  val kafkaConsumer: ActorRef = system.actorOf(KafkaConsumer.props("myevents"), "consumertest")

  // start consuming events on the "test" group
  kafkaConsumer ! "test"

  def listBooks = Action {
    kafkaProducer ! """{ "event": "books viewed" }"""
    Ok(Json.toJson(books))
  }

  def saveBook = Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Book]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toFlatJson(errors)))
      },
      book => {
        addBook(book)
        kafkaProducer ! """{ "event": "book saved" }"""
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }
}

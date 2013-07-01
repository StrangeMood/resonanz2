package models

import play.api.db.slick.Config.driver.simple._

case class Conversation(id: Option[Int], key: String)

object Conversations extends Table[Conversation]("conversations") with FindById[Conversation] {
  def key = column[String]("key")

  def * = id.? ~ key <> (Conversation, Conversation.unapply _)

  def autoInc = key returning id
}

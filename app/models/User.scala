package models

import play.api.db.slick.Config.driver.simple._

case class User(id: Option[Int], name: String)

object Users extends Table[User]("users") with FindById[User] {
  def name = column[String]("name")

  def * = id.? ~ name <> (User, User.unapply _)

  def autoInc = name returning id
}

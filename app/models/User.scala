package models

import play.api.db.slick.Config.driver.simple._

case class User(id: Option[Int], name: String, token: String)

object Users extends Table[User]("users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def token = column[String]("token")

  def * = id.? ~ name ~ token <> (User, User.unapply _)
}

package models

import play.api.db.slick.Config.driver.simple._

case class User(id: Option[Int], name: String)

object Users extends Table[User]("users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = id.? ~ name <> (User, User.unapply _)

  def autoInc = id.? ~ name <> (User, User.unapply _) returning id
}

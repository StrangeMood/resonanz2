package models

import play.api.db.slick.Config.driver.simple._

case class User(id: Option[Int], name: String)

object Users extends Table[User]("users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = id.? ~ name <> (User, User.unapply _)

  def autoInc = name returning id

  val usersById = for {
    id <- Parameters[Int]
    u <- Users if u.id is id
  } yield u

  def find(id: Int) = usersById(id)
  def find(id: String) = usersById(id.toInt)
}

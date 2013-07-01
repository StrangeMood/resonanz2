package models

import play.api.db.slick.Config.driver.simple._

trait FindById[T] {
  self: Table[T] =>

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  val findById = for {
    id <- Parameters[Int]
    record <- self if record.id is id
  } yield record

  def find(id: Int) = findById(id)
  def find(id: String) = findById(id.toInt)
}

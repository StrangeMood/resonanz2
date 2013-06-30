package controllers

import play.api._
import play.api.mvc._
import models.{HaikuNames, Users, User}
import play.api.db.slick.DB
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}

trait Secured {
  self: Controller =>

  def user_id(request: RequestHeader) = request.session.get("user_id")

  def onUnauthorized(request: RequestHeader) = Action {
    implicit request =>
      DB.withSession {
        implicit session =>
          val userId = Users.autoInc.insert(User(None, HaikuNames.name))

          Redirect(routes.Application.index).withSession("user_id" -> userId.toString)
      }
  }

}

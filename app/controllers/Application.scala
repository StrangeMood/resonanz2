package controllers

import play.api._
import play.api.mvc._
import models.{HaikuNames, Users, User}
import play.api.db.slick.DB
import play.api.Play.current

object Application extends Controller with Secured {

  def index = Authenticated { implicit user =>
    request =>
      Ok(views.html.index("GREETINGS"))
  }

  def changeIdentity = Action(onUnauthorized _)

}

trait Secured {
  self: Controller =>

  def userId(request: RequestHeader) = request.session.get("user_id")

  def onUnauthorized(request: RequestHeader):Result = {
    DB.withSession {
      implicit session =>
        val userId = Users.autoInc.insert(HaikuNames.name)

        Results.Redirect(routes.Application.index).withSession(request.session + ("user_id" -> userId.toString))
    }
  }

  def Authenticated(f: => User => Request[AnyContent] => Result) =
    Security.Authenticated(userId, onUnauthorized) { userId =>
      DB.withSession { implicit s =>
        Users.find(userId).firstOption match {
          case Some(user) => Action(request => f(user)(request))
          case _ => Action(onUnauthorized _)
        }
      }
    }
}

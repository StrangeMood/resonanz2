package controllers

import play.api.mvc._

object Conversations extends Controller with Secured {

  def show(key: String) = Authenticated { implicit user =>
    request =>
      Ok(views.html.conversations.show(key))
  }

}

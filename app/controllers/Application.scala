package controllers

import play.api._
import play.api.mvc._
//http://blog.echo.sh/post/65955606729/exploring-scala-macros-map-to-case-class-conversion
object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
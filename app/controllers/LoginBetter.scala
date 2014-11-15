package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data._
import play.api.data.Forms._
import models.LoginRequest
import service.UserService

//https://www.playframework.com/documentation/2.3.x/ScalaForms
object LoginBetter extends Controller{

		def betterLoginForm = Form(
				mapping("username" -> nonEmptyText, "password" -> nonEmptyText)
				(LoginRequest.apply)(LoginRequest.unapply)
				verifying("Failed form constraints!", fields => fields match {
					case loginRequest => validate(loginRequest.username, loginRequest.password).isDefined
				}))
		
		def getLogin = Action {
			Ok(views.html.betterLogin(betterLoginForm))
	  }
		
	
		def betterLogin = Action {
			implicit request => betterLoginForm.bindFromRequest().fold(
					hasErrors => BadRequest(views.html.betterLogin(hasErrors)),
					success		=> {
						//LoginService check and NotFound or Ok
						UserService.addUser(success)
						Ok(views.html.index(""))
					}
				)
						
		}
		
		def validate(name: String, password: String) = {
			name match {
				case "admin" if "admin" == password => Some(LoginRequest(name, password))
				case "test"  if "test" == password  => Some(LoginRequest(name, password))
				case _		 													=> None
			}
		} 
}
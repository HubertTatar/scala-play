package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data._
import play.api.data.Forms._
import user.service.UserService
import models.LoginRequest

/*
/*
 * TODO user context, adding to ctx when logged and removeing when logout, roles
 */
object Login extends Controller {
	
	def getLogin = Action {
		Ok(views.html.login(""))
	}
	
	def postLogin = Action {
			implicit request => val loginRequest = loginForm.bindFromRequest.get
			
			if ("test" == loginRequest.username) {
				UserService.addUser(loginRequest)
				Ok(views.html.index(""))
			} else { 
				NotFound(views.html.login("Wrong username or password"))
			}
	}
	
	def logout = Action {
		Ok(views.html.index("Logged out succesfully."))
	}

	
	def loginForm = Form(mapping("username" -> text, "password" -> text)(LoginRequest.apply)(LoginRequest.unapply))
}
*/
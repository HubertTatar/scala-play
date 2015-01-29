package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data._
import play.api.data.Forms._
import user.service.UserService
import models.LoginRequest

//https://www.playframework.com/documentation/2.3.x/ScalaForms
//https://www.owasp.org/index.php/Authentication_Cheat_Sheet#Implement_Proper_Password_Strength_Controls
//http://stackoverflow.com/questions/12100698/play-framework-2-0-validate-field-in-forms-using-other-fields/19877822#19877822
//http://workwithplay.com/blog/2013/07/10/advanced-forms-techniques/
object LoginBetter extends Controller {

	def betterLoginForm = Form(
		mapping("username" -> nonEmptyText, "password" -> nonEmptyText)(LoginRequest.apply)(LoginRequest.unapply)
			verifying ("Bad username/password!", fields => fields match {
				case loginRequest => true//LoginRequest.userExist(loginRequest)
			}))

	def getLogin = Action {
		Ok(views.html.betterLogin(betterLoginForm))
	}

	def betterLogin = Action {
		implicit request =>
			betterLoginForm.bindFromRequest().fold(
				hasErrors => BadRequest(views.html.betterLogin(hasErrors)),
				success => {
//					val user = LoginRequest.userExist(success)
//					if (user.isDefined) {
//						LoginRequest.validateUserStatus(user.get)//TODO walidownie i logika z tym zwiazana
//					}
					
					//LoginService check and NotFound or Ok
					//						UserService.addUser(success)
					Redirect(routes.Application.index())
					//Ok(views.html.index(""))
				})
	}
	
}
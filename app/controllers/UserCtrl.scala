package controllers

import models.User
import dao.UserDao
import play.api.mvc.{Controller,Action}
import play.api.Logger
import play.api.data._
import play.api.data.Forms._

object UserCtrl extends Controller {
	val list: Seq[User] = UserDao.list
	
	def users = Action {
		Ok(views.html.userList(list))
	}
	
	def newUser = Action {
		Ok(views.html.newUser(userForm))
	}
	
	def addNewUser = Action {
		implicit request => userForm.bindFromRequest().fold(
			formWithErrors => BadRequest(views.html.newUser(formWithErrors)),
			newUser  => {
					UserDao.addUser(newUser)
					Ok(views.html.userList(list))
				}
			)
	}
	
	def userForm = Form(mapping("id" -> number, "name" -> nonEmptyText, "password" -> nonEmptyText, "active" -> nonEmptyText, "blocked" -> nonEmptyText)(User.apply)(User.unapply))
}
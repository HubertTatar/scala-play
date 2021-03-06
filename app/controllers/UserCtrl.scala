package controllers

import user.dao.UserDao
import play.api.mvc.{Controller,Action}
import play.api.Logger
import play.api.data._
import play.api.data.Forms._
import user.dto.User

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
					Logger.info("Insert " + UserDao.insert(newUser))
					Ok(views.html.userList(list))
				}
			)
	}
	
	def userForm = Form(mapping("id" -> longNumber, "name" -> nonEmptyText, "password" -> nonEmptyText, "active" -> nonEmptyText, "blocked" -> nonEmptyText, "version" -> longNumber)(User.apply)(User.unapply))
}
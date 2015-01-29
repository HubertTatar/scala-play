package models

import user.service.UserService
import user.dto.User

case class LoginRequest(username: String, password: String)

object LoginRequest {
	def userExist(loginRequest: LoginRequest) = {
		UserService.userExists(loginRequest)
		//			name match {
		//				case "admin" if "admin" == password => Some(LoginRequest(name, password))
		//				case "test"  if "test" == password  => Some(LoginRequest(name, password))
		//				case _		 													=> None
		//			}
	}
	
	def validateUserStatus(user: User): Boolean = user.blocked == "Y" && user.active == "Y"
}
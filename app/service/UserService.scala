package service

import models.LoginRequest


//http://alvinalexander.com/scala/scala-maps-map-class-examples
object UserService {
	var loggedUsers: scala.collection.mutable.Map[String, LoginRequest] = scala.collection.mutable.Map[String, LoginRequest]() 	
	
	def addUser(user: LoginRequest) = loggedUsers += (user.username -> user)
	
	def removeUser(user: LoginRequest) = loggedUsers -= user.username
	
	def isLoggedIn(user: LoginRequest) = loggedUsers.contains(user.username)
}
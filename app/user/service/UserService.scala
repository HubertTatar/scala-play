package user.service

import models.LoginRequest
import models.LoginRequest
import user.dao.UserDao
import user.dto.User

//http://alvinalexander.com/scala/scala-maps-map-class-examples
//http://workwithplay.com/blog/2013/07/10/advanced-forms-techniques/
//http://jonasboner.com/2008/10/06/real-world-scala-dependency-injection-di/   - cake pattern
//TODO - implementacja cake pattern ??
object UserService {

  var loggedUsers: scala.collection.mutable.Map[String, LoginRequest] = scala.collection.mutable.Map[String, LoginRequest]()

  private def addUser(user: LoginRequest) = loggedUsers += (user.username -> user)

  private def removeUser(user: LoginRequest) = loggedUsers -= user.username

  def isLoggedIn(user: LoginRequest) = loggedUsers.contains(user.username)

  def userExists(user: LoginRequest): Option[User] = {
    UserDao.getByName(user.username)
  }
}
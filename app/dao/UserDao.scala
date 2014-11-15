package dao

import com.typesafe.slick.driver.oracle.OracleDriver.simple._
import bootstrap.Global
import models.Users
import models.User
import models.User

object UserDao {
	
	private val users = TableQuery[Users]
	private val db = Global.getDB()//TODO do jakiesj nadklasy
	
	def addUser(newUsr: User) = db.withSession(implicit session => users += newUsr)
	
	def list() = {
		db.withSession(implicit session =>  users.list)
	}
	def createUsers(){
		db.withSession(implicit session => users +=	 User(0, "admin", "admin", "Y","Y"))
	}

}
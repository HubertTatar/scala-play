package models

import com.typesafe.slick.driver.oracle.OracleDriver.simple._

//http://stackoverflow.com/questions/21894377/returning-autoinc-id-after-insert-in-slick-2-0
case class User(id:Int, name: String, password: String, active: String, blocked:String)

class Users(tag: Tag) extends Table[User](tag, "USERS") {
	def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
	def name = column[String]("NAME", O.NotNull) 
	def password = column[String]("PASSWORD", O.NotNull) 
	def active = column[String]("ACTIVE", O.NotNull) 
	def blocked = column[String]("BLOCKED", O.NotNull) 
	
	def * = (id, name, password, active, blocked) <> (User.tupled, User.unapply)

}
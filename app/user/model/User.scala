package user.model

import com.typesafe.slick.driver.oracle.OracleDriver.simple._
import user.dto.User
import bootstrap.Global

//http://stackoverflow.com/questions/21894377/returning-autoinc-id-after-insert-in-slick-2-0
//http://slick.typesafe.com/doc/2.1.0/userdefined.html
class Users(tag: Tag) extends Table[User](tag, "USERS") {
	def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
	def name = column[String]("NAME", O.NotNull) 
	def password = column[String]("PASSWORD", O.NotNull) 
	def active = column[String]("ACTIVE", O.NotNull) 
	def blocked = column[String]("BLOCKED", O.NotNull) 
	
	def * = (id, name, password, active, blocked) <> (User.tupled, User.unapply)
}

object UsersDatabase {
	val users = TableQuery[Users]
	val db = Global.getDB()
  implicit val session = db.createSession()
}

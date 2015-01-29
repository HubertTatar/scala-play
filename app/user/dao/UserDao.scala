package user.dao

import user.model.UsersDatabase._
import user.dto.User
import com.typesafe.slick.driver.oracle.OracleDriver.simple._

object UserDao {

	def list = users.list
	
	def getByName(name: String) = users.filter ( _.name === name ).firstOption
	
	def getById(id: Long) = users.filter ( _.id === id ).firstOption
	
	def addUser(user: User) = users += user
	
	def update(user: User) = users.filter ( _.id === user.id ).update(user)

  def delete(id: Long) = users.filter ( _.id === id ).delete
  
}
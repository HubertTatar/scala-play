package user.dao

import com.typesafe.slick.driver.oracle.OracleDriver.simple._
import common.dao.GenericVersionedDao
import user.dto.User
import user.model.Users

object UserDao extends GenericVersionedDao[Users, User] {

  override val tableReference: TableQuery[Users] = TableQuery[Users]
  
  def getByName(name: String) = tableReference.filter ( _.name === name ).firstOption
  
}
package dao

import com.typesafe.slick.driver.oracle.OracleDriver.simple._
import bootstrap.Global
import models.employee.{Employees, Employee}

object EmployeeDao {
	private val employees = TableQuery[Employees]
	private val db = Global.getDB()
	
	def findByName(name: String): Option[Employee] = {
    db.withSession { implicit session =>
      val query = for {
        e <- employees if e.firstName === name
      } yield e
      query.firstOption
    }	
	}
	
	def findById(id: Int): Option[Employee] = {
		db.withSession { implicit session =>
      val query = for {
        e <- employees if e.employeeId === id
      } yield e
      query.firstOption
    }
	}
	
	def saveEmployee(employee: Employee) = db.withSession { implicit session => employees += employee }
	
	def list() = db.withSession { implicit session => employees.list }
}
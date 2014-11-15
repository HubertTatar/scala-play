package models.employee

import java.util.Date
import com.typesafe.slick.driver.oracle.OracleDriver.simple._
//TODO
//http://blog.lunatech.com/2013/11/21/slick-case-classes
//http://tikokelottlegyenviz.blogspot.com/2013/08/scala-slick-left-join.html
//http://blog.lunatech.com/2013/08/21/slick-column-definitions
//http://stackoverflow.com/questions/17840079/slick-scala-not-inserting-any-values
case class Employee(employeeId: Int, firstName: String, lastName: String, email: String, phoneNumber: Option[String], salary: Option[Double], jobId: Option[String], commisionPct: Option[Double], managerId: Option[Int], departmentId: Option[Int]) {
}

class Employees(tag: Tag) extends Table[Employee](tag, "EMPLOYEES") {
//	implicit val JavaUtilDateMapper =
//    MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
//      d => new java.sql.Timestamp(d.getTime),
//      d => new java.util.Date(d.getTime))
	
	def employeeId 		= column[Int]("EMPLOYEE_ID", O.PrimaryKey, O.AutoInc)
	def firstName  		= column[String]("FIRST_NAME", O.NotNull)
	def lastName	 		= column[String]("LAST_NAME", O.NotNull)
	def email			 		= column[String]("EMAIL", O.NotNull)
	def phoneNumber		= column[Option[String]]("PHONE_NUMBER")
	def salary				= column[Option[Double]]("SALARY")
	def jobId					= column[Option[String]]("JOB_ID")
	def commisionPct  = column[Option[Double]]("COMMISSION_PCT")
	def managerId		  = column[Option[Int]]("MANAGER_ID")
	def departmentId	= column[Option[Int]]("DEPARTMENT_ID")
	
	def * = (employeeId, firstName, lastName, email, phoneNumber, salary, jobId, commisionPct, managerId, departmentId) <> (Employee.tupled,Employee.unapply _)
}
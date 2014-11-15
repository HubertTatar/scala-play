package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import dao.EmployeeDao
import models.employee.Employee
import play.api.data._
import play.api.data.Forms._
import models.employee.Employee
import models.employee.EmployeeShort
import models.employee.EmployeeShort
import models.employee.EmployeeShort
import models.employee.Employee
import models.employee.Employee

object EmployeesCtrl extends Controller {
	
	def employees = Action {
		val list: Seq[Employee] = EmployeeDao.list
		Ok(views.html.employeesList(list))
	}
	
	def byId(id: Int) = Action {
		val employee = EmployeeDao.findById(id)
		if (employee.isDefined) {
			Ok(views.html.employeeDetail(employee.get))
		} else {
			NotFound(views.html.employeeDetail(employee.get))//TODO ??
		}
	}
	
	def getCreateEmployee = Action {
		Ok(views.html.employeeCreate(employeeForm))
	}
	//https://www.playframework.com/documentation/2.2.x/ScalaForms
	def createEmployee = Action {
		implicit request => employeeForm.bindFromRequest().fold(
			formWithErrors => BadRequest(views.html.employeeCreate(formWithErrors)),
			employeeShort  => {
				Ok(s"Employee ${employeeShort.firstName} created successfully")
				}
			
			//val employee = EmployeeDao.create(employeeShort)
			//Ok(views.html.employeeDetail(employee.get))
		)
	}
	
	
	def employeeForm = Form(mapping("First name" -> nonEmptyText, "Last name" -> nonEmptyText, "Email" -> nonEmptyText)(EmployeeShort.apply)(EmployeeShort.unapply))
}
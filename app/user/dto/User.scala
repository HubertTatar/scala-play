package user.dto

case class User(id:Long, name: String, password: String, active: String, blocked:String, version: Long)
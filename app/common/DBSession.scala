package common

import bootstrap.Global

trait DBSession {
  val db = Global.getDB()
  implicit val session = db.createSession()
}
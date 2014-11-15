package bootstrap

import com.typesafe.slick.driver.oracle.OracleDriver.backend.DatabaseDef
import com.typesafe.slick.driver.oracle.OracleDriver.simple._
import play.api.GlobalSettings
import play.api.Application
import play.api.Play.current
import play.api.Logger
import play.api.db.DB

object Global extends GlobalSettings{
	
	override def onStart(app: Application) {
		Logger.info("Application has started")
	}	
	
	override def onStop(app: Application) {
		Logger.info("Shutting down.")
	}
	
	def getDB(): DatabaseDef =  { Database.forDataSource(DB.getDataSource()) }
}
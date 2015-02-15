package common.dao

import com.typesafe.slick.driver.oracle.OracleDriver.simple._
import common.table.IdTable
import play.api.db.DB
import bootstrap.Global
import common.DBSession
import java.sql.SQLException
import play.api.Logger

trait GenericIdDao[T <: IdTable[A], A] extends DBSession {

  def tableReference: TableQuery[T]

  def list = tableReference.list
  
  def insert(row: T#TableElementType) = {
    try {
    	tableReference.insert(row)
    } catch {
      case e: SQLException => {
          Logger.error(e.getMessage)
          0
      }
    }  
    
    }

  def insertAndGetId(row: T#TableElementType) = (tableReference returning tableReference.map(_.id)) += row

  def deleteById(id: Long): Boolean = tableReference.filter(_.id === id).delete == 1
  
  def updateById(id: Long, row: T#TableElementType): Boolean = tableReference.filter(_.id === id).update(row) == 1
  
  //I'm to lame to understand usage of tableElement..
  //def selectById(id: Long): Option[T#TableElementType] = tableReference.filter(_.id === id).firstOption
  def selectById(id: Long): Option[A] = tableReference.filter(_.id === id).firstOption

  def existsById(id: Long): Boolean = {
    (for {
      row <- tableReference
      if row.id === id
    } yield row).firstOption.isDefined
  }
}
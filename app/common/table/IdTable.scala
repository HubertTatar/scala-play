package common.table

import com.typesafe.slick.driver.oracle.OracleDriver.simple._

abstract class IdTable[T](tag: Tag, name: String) extends Table[T](tag, name) {
  val id: Column[Long] = column[Long]("ID", O.PrimaryKey, O.AutoInc)
}
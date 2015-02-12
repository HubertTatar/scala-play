package common.table

import com.typesafe.slick.driver.oracle.OracleDriver.simple._

abstract class VersionedTable[T](tag: Tag, name: String) extends IdTable[T](tag, name) {
  val version: Column[Long] = column[Long]("VERSION", O.NotNull)
}
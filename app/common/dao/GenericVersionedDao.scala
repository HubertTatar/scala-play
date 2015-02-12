package common.dao

import com.typesafe.slick.driver.oracle.OracleDriver.simple._
import common.table.VersionedTable
import common.DBSession

//http://lampwww.epfl.ch/~michelou/scala/scala-advanced-features.html
trait GenericVersionedDao[T <: VersionedTable[A], A] extends GenericIdDao[T, A] {

  def deleteById(id: Long, version: Long): Boolean =
    tableReference.filter(x => (x.id === id && x.version === version)).delete == 1

  def updateById(id: Long, version: Long, row: T#TableElementType): Boolean =
    tableReference.filter(x => (x.id === id && x.version === version)).update(row) == 1

}
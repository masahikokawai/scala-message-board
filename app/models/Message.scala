package models

import scalikejdbc._
import skinny.orm._

/**
  * Message
  */
case class Message(id: Option[Long])

object Message extends SkinnyCRUDMapper[Message] {

  override def defaultAlias: Alias[Message] = ??? // FIXME

  override def extract(rs: WrappedResultSet, n: ResultName[Message]): Message =
    autoConstruct(rs, n)

  private def toNamedValues(record: Message): Seq[(Symbol, Any)] = ??? // FIXME

  // ヘルパーメソッド
  def create(message: Message)(implicit session: DBSession): Long =
    createWithAttributes(toNamedValues(message): _*)

  // ヘルパーメソッド(ただし、全カラムを更新するので、部分更新にする場合はupdateByIdを直接利用すること)
  def update(message: Message)(implicit session: DBSession): Int =
    updateById(message.id.get).withAttributes(toNamedValues(message): _*)

}

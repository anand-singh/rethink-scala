package com.rethinkscala.net

import org.jboss.netty.channel.Channel
import ql2.VersionDummy


/**
 * Created with IntelliJ IDEA.
 * User: keyston
 * Date: 7/3/13
 * Time: 5:22 PM
 *
 */
abstract class Version {

  val host: String
  val port: Int
  val maxConnections: Int
  val db: Option[String]
  val queryMode: QueryMode

  def configure(c: Channel)
}

case class Version1(host: String = "localhost", port: Int = 28015, db: Option[String] = None, maxConnections: Int = 5, queryMode: QueryMode = Blocking) extends Version {
  def configure(c: Channel) {
    c.write(VersionDummy.Version.V0_1).await()
  }


}

case class Version2(host: String = "localhost", port: Int = 28015, db: Option[String] = None, maxConnections: Int = 5, authKey: String = "", queryMode: QueryMode = Blocking) extends Version {
  def configure(c: Channel) {
    c.write(VersionDummy.Version.V0_1)
    c.write(authKey).await()
  }
}
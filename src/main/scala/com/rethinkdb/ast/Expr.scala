package com.rethinkdb.ast

import com.rethinkdb.Term

/**
 * Created with IntelliJ IDEA.
 * User: keyston
 * Date: 4/2/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
object Expr {
  /*import reflect._
  class Def[C](implicit desired : Manifest[C]) {
     def unapply[X](c : X)(implicit m : Manifest[X]) : Option[C] = {
         def sameArgs = desired.typeArguments.zip(m.typeArguments).forall {case (desired,actual) => desired >:> actual}
         if (desired >:> m && sameArgs) Some(c.asInstanceOf[C])
         else None
       }
     }

  val DefMap=new Def[Map[String,Any]]
  val DefSeq=new Def[Seq[Any]]    */
  def apply(term: Term): Term = term

  def apply(value: Seq[Any]): Term = MakeArray(value)

  def apply(value: Map[String, Any]): Term = MakeObj(value)

  def apply(a: Any): Term = {
    val b = a
    a match {
      case t: Term => t
      case s: Seq[_] => MakeArray(s)
      case m: Map[_, _] => MakeObj(m.asInstanceOf[Map[String, Any]])
      case a: Any => Datum(a)


    }
  }

}
/**
 * Created by darioalessandro on 3/3/15.
 */

package com.kittens

case class Kitten(id: Long, attributes: Seq[String])
case class BuyerPreferences(attributes: Seq[String])

//val prefs = BuyerPreferences(List("male", "tabby", "kid-friendly"))

//prefs.attributes.map(attribute => casmiler.attributes.contains(attribute))

//res1 map (matched => if(matched) 1.0 else 0)

//res2.sum / res2.length

object Logic {
  def matchLikelihood(kitten: Kitten, buyer: BuyerPreferences): Double = {
    val matches = buyer.attributes map { attribute => kitten.attributes contains attribute}
    val nums = matches map { b => if(b) 1.0 else 0.0 }
    nums.sum / nums.length
  }
}



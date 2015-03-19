package com.blackfireapps

/**
 * Created by darioalessandro on 3/18/15.
 *
 */

import sbt._
import sbt.Keys._
import complete.DefaultParsers._

object HelloPlugin extends sbt.AutoPlugin {

  lazy val helloTask = taskKey[Unit]("helloTask")

  override def projectSettings = Seq(
    helloTask := println("Hello " + (scalaSource in Compile).value.getAbsolutePath)
  )

}

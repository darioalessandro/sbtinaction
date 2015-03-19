package com.blackfireapps

/**
 * Created by darioalessandro on 3/18/15.
 *
 */

import sbt._

object HelloPlugin extends sbt.AutoPlugin {

  lazy val helloTask = taskKey[Unit]("helloTask")

  override def projectSettings = Seq(
    helloTask := println("Hello world")
  )

}

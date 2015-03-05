name := """kittens"""

version := "1.0"

scalaVersion := "2.11.5"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.specs2" % "specs2_2.11" % "2.3.11"
)

def PreownedKittenProject(name: String) : Project = Project(name,file(name)).settings(
  version := "1.0",

  organization := "com.preownedkittens",

  libraryDependencies ++= Seq(
    "org.specs2" % "specs2_2.11" % "2.3.11"
  )
)

autoScalaLibrary := false

organization := "General Motors LLC"

val gitHeadCommitSha = TaskKey[String]("gitHeadCommitSha")

gitHeadCommitSha := Process("git rev-parse HEAD").lines.head

val taskA = TaskKey[String]("taskA")

val taskB = TaskKey[String]("taskB")

val taskC = TaskKey[String]("taskC")

taskA := { val b = taskB.value;  val c = taskC.value;  "taskA" }

taskB := { Thread.sleep(5000); "taskB" }

taskC := { Thread.sleep(5000); "taskC" }

val sampleIntTask = TaskKey[Int]("sampleIntTask")

sampleIntTask := {
  val sum = 1 + 5
  println("sum: " + sum)
  sum
}

val makeVersionProperties = TaskKey[Seq[File]]("makeVersionProperties")

makeVersionProperties := {
  val propFile = new File((resourceManaged in Compile).value, "version.properties")
  val content = s"version=${gitHeadCommitSha.value}"
  IO.write(propFile, content)
  Seq(propFile)
}

resourceGenerators in Compile += makeVersionProperties.taskValue

lazy val common = PreownedKittenProject("common")
  .settings()

lazy val analytics =
  PreownedKittenProject("analytics")
    .dependsOn(common)
    .settings()


lazy val website =
  PreownedKittenProject("website")
    .dependsOn(common)
    .settings()

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

mappings in packageBin in Compile += (baseDirectory.value / “LICENSE”) -> “PREOWNED-KITTEN-LICENSE”

import kotlin.Keys.{ kotlinLib, kotlinVersion }

ThisBuild / version       := "0.1.0-SNAPSHOT"
ThisBuild / organization  := "com.github.wenjunhuang"
ThisBuild / scalaVersion  := "3.4.1"
ThisBuild / kotlinVersion := "1.9.24"

lazy val leetcodeScala = (project in file("leetcode_scala"))
  .settings(
    scalaVersion                           := "3.4.1",
    name                                   := "leetcode_scala",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )

lazy val leetcodeKotlin = (project in file("leetcode_kotlin"))
  .settings(
    name := "leetcode_kotlin",
    kotlinLib("stdlib"),
    // add kotest dependency
    libraryDependencies ++=
      Seq(
        "io.kotest" % "kotest-assertions-core" % "5.9.0",
        "io.kotest" % "kotest-runner-junit5"   % "5.9.0"
      )
  )
  .enablePlugins(KotlinPlugin)

lazy val leetcodeJava = (project in file("leetcode_java"))
  .settings(
    name := "leetcode_java",
    // add junit5
    libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.10.2" % Test
  )

lazy val designPattern = (project in file("design_pattern"))
  .settings(
    name                                   := "design_pattern",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )

lazy val fpLearn = (project in file("fp"))
  .settings(
    name := "functional learn",
    libraryDependencies ++=
      Seq(
        "org.typelevel" %% "cats-effect" % "3.5.4",
        "org.typelevel" %% "cats-core"   % "2.12.0",
        "org.scalatest" %% "scalatest"   % "3.2.19" % Test
      )
  )

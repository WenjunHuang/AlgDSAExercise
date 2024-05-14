import kotlin.Keys.kotlinLib

ThisBuild / version := "0.1.0-SNAPSHOT"


lazy val leetCodeScala = (project in file("leetcode_scala"))
  .settings(
    scalaVersion := "3.4.1",
    name := "leetcode_scala",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )

lazy val leetCodeKotlin = (project in file("leetcode_kotlin"))
  .settings(
    name := "leetcode_kotlin",
    kotlinLib("stdlib"),
    // add kotest dependency
    libraryDependencies ++=
      Seq(
        "com.novocode" % "junit-interface" % "0.11" % Test,
        "io.kotest" % "kotest-assertions-core" % "5.9.0",
        "io.kotest" % "kotest-runner-junit5" % "5.9.0"
      )
  )
  .enablePlugins(KotlinPlugin)

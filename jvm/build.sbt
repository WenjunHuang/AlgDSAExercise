import kotlin.Keys.{kotlinLib, kotlinVersion}

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
    kotlinVersion := "1.9.24",
    // add kotest dependency
    libraryDependencies ++=
      Seq(
        "io.kotest" % "kotest-assertions-core" % "5.9.0",
        "io.kotest" % "kotest-runner-junit5" % "5.9.0"
      )
  )
  .enablePlugins(KotlinPlugin)

lazy val leetCodeJava = (project in file("leetcode_java"))
  .settings(
    name := "leetcode_java",
    // add junit5
    libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.10.2" %  Test
  )

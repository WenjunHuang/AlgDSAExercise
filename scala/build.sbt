ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.1"

lazy val leetCodeCn = (project in file("leetcode"))
  .settings(
    name := "leetcode_cn"
  )
  .enablePlugins(KotlinPlugin)

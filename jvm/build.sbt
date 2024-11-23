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

//lazy val leetcodeKotlin = (project in file("leetcode_kotlin"))
//  .settings(
//    name := "leetcode_kotlin",
//    kotlinLib("stdlib"),
//    // add kotest dependency
//    libraryDependencies ++=
//      Seq(
//        "io.kotest" % "kotest-assertions-core" % "5.9.0",
//        "io.kotest" % "kotest-runner-junit5"   % "5.9.0"
//      )
//  )
//  .enablePlugins(KotlinPlugin)
//
//lazy val leetcodeJava = (project in file("leetcode_java"))
//  .settings(
//    name := "leetcode_java",
//    // add junit5
//    libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.10.2" % Test
//  )

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
        "org.typelevel" %% "cats-effect" % "3.5.6",
        "org.typelevel" %% "cats-core"   % "2.12.0",
        "dev.zio"       %% "zio"         % "2.1.13",
        "org.typelevel" %% "squants"     % "1.8.3",
        // add circe

        "io.circe" %% "circe-core"    % "0.14.10",
        "io.circe" %% "circe-generic" % "0.14.10",
        "io.circe" %% "circe-parser"  % "0.14.10",
//        "io.circe" %% "circe-generic-extras" % "0.14.10",
        // add redis4cats
        "dev.profunktor" %% "redis4cats-effects" % "1.7.1",
        // ad monocle
        "dev.optics" %% "monocle-core" % "3.3.0",
        // add cats retry
        "com.github.cb372" %% "cats-retry" % "3.1.3",
        // add log4cats
        "org.typelevel" %% "log4cats-slf4j" % "2.7.0",
        // add http4s client
        "org.http4s" %% "http4s-client"       % "0.23.29",
        "org.http4s" %% "http4s-dsl"          % "0.23.29",
        "org.http4s" %% "http4s-circe"        % "0.23.29",
        "org.http4s" %% "http4s-blaze-client" % "0.23.17",
        "org.http4s" %% "http4s-blaze-server" % "0.23.17",
        // add fs2
        "co.fs2"        %% "fs2-core"  % "3.11.0",
        "org.scalatest" %% "scalatest" % "3.2.19" % Test
      )
  )

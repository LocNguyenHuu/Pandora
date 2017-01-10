name := "Pandora"

version := "1.0"

lazy val `pandora` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc ,
  cache ,
  ws   ,
  specs2 % Test,
  "org.slf4j"           % "slf4j-nop"  % "1.6.4",
  "net.debasishg" %% "redisclient" % "3.0",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "com.zaxxer" % "HikariCP" % "2.4.1")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  
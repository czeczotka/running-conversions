name := "running-conversions"

version := "0.2"

scalaVersion := "2.12.1"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.8.6" % "test"

exportJars := true

organization := "com.agynamix"

name := "LiftBasic"

version := "0.1"

scalaVersion := "2.9.1"

// scalacOptions += "-deprecation"

seq(webSettings :_*)

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

resolvers += "Lift Snapshots" at "http://scala-tools.org/repo-snapshots/"

resolvers += "Scala Releases" at "http://scala-tools.org/repo-releases/"

libraryDependencies ++= {
    val liftVersion = "2.4"
    Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default")
  }

// "org.mortbay.jetty" % "jetty" % "6.1.22" % "jetty,test", // For Jetty 6, add scope test to make jetty avl. for tests
// "org.eclipse.jetty" % "jetty-webapp" % "7.3.0.v20110203" % "jetty,test", // For Jetty 7

libraryDependencies ++= Seq(
  "org.mortbay.jetty" % "jetty" % "6.1.22" % "jetty,test", // For Jetty 6, add scope test to make jetty avl. for tests
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "org.specs2" %% "specs2" % "1.7.1" % "test",
  "org.pegdown" % "pegdown" % "1.0.2" % "test",
  "org.scalatest" %% "scalatest" % "1.6.1" % "test->default",
  "com.h2database" % "h2" % "1.2.138",
  "org.slf4j" % "slf4j-simple" % "1.6.1" % "compile",
  "org.seleniumhq.selenium" % "selenium-java" % "2.17.0" % "test->default"
)

// jettyClasspaths <<= (jettyClasspaths, sourceDirectory).map((j, src) => j.copy(classpath = j.classpath +++ src / "development" / "resources"))

// If using JRebel uncomment next line
jettyScanDirs := Nil

temporaryWarPath <<= (sourceDirectory in Compile)(_ / "webapp")

// keep only specifications ending with Spec or Unit
//testOptions := Seq(Tests.Filter(s => Seq("Spec", "Unit").exists(s.endsWith(_))))

//If you don't want the specifications to be executed in parallel:
//parallelExecution in Test := false

// Create HTML output
testOptions in Test += Tests.Argument("html")

testOptions in Test += Tests.Argument("console")

// Run it with
// sbt test-only allSpecs -- console html
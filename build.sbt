name := "swagger-play2"

version := "1.3.5"

  checksums in update := Nil

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.1.3",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.1.4",
  "org.slf4j" % "slf4j-api" % "1.6.4",
  "com.wordnik" % "swagger-jaxrs_2.10" % "1.3.4",
  "javax.ws.rs" % "jsr311-api" % "1.1.1",
  "org.mockito" % "mockito-core" % "1.9.5" % "test")

lazy val root = (project in file(".")).addPlugins(PlayScala).settings(
  resolvers := Seq(
    "maven-central" at "https://repo1.maven.org/maven2",
    "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
    "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases",
    "java-net" at "http://download.java.net/maven/2",
    "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"))  

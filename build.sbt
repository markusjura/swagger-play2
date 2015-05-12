import sbtrelease.ReleasePlugin._
import sbtrelease.ReleaseStateTransformations._

organization := "com.markusjura"

name := "swagger-play2"

scalaVersion := "2.11.6"

crossScalaVersions := Seq("2.11.6", "2.10.4")

checksums in update := Nil

lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
  releaseSettings
)

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.5.2",
  "com.fasterxml.jackson.core"   %  "jackson-annotations"  % "2.5.2",
  "org.slf4j"                    %  "slf4j-api"            % "1.7.12",
  "com.wordnik"                  %% "swagger-jaxrs"        % "1.3.12",
  "javax.ws.rs"                  %  "jsr311-api"           % "1.1.1",
  "org.mockito"                  %  "mockito-core"         % "1.9.5"   % "test"
)

homepage := Some(url("https://github.com/markusjura/swagger-play2"))

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

// Release settings
ReleaseKeys.versionBump := sbtrelease.Version.Bump.Minor

// Bintray settings
publishMavenStyle := false

publishArtifact in Test := false

bintrayRepository := "swagger-play2"

// Scala option settings
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.6",
  "-encoding", "UTF-8"
)
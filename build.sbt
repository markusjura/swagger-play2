import sbtrelease.ReleasePlugin._

organization := "com.markusjura"

name := "swagger-play2"

scalaVersion := "2.11.6"

crossScalaVersions := Seq("2.11.6", "2.10.4")

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

// Release settings
ReleaseKeys.versionBump := sbtrelease.Version.Bump.Bugfix
ReleaseKeys.publishArtifactsAction := PgpKeys.publishSigned.value

// Publish settings
bintrayRepository := "maven"
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
homepage := Some(url("https://github.com/markusjura/swagger-play2"))
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
pomExtra := (
  <scm>
    <url>git@github.com:markusjura/swagger-play2.git</url>
    <connection>scm:git:git@github.com:markusjura/swagger-play2.git</connection>
  </scm>
  <developers>
    <developer>
      <id>markusjura</id>
      <name>Markus Jura</name>
      <url>https://github.com/markusjura</url>
    </developer>
  </developers>)

// Scala option settings
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.6",
  "-encoding", "UTF-8"
)
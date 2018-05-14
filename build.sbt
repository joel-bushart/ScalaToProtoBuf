name := "sbt-CaseClassToProtoBuf"

version := "0.1"

scalaVersion := "2.12.6"

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

scalacOptions += "-Ypartial-unification"

resolvers := Seq(
  "Artifactory Realm " at "https://repo.artifacts.weather.com/sun-release-virtual",
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
//  Resolver.mavenCentral,
  Resolver.bintrayRepo("julien-lafont", "maven"),
  Resolver.defaultLocal
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.typelevel" %% "cats-core" % "1.0.1",

  //testing
  "org.typelevel" %% "cats-testkit" % "1.0.1"
).map(_.withSources().withJavadoc())


sbtPlugin := true
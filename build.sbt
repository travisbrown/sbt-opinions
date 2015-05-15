sbtPlugin := true
organization := "io.travisbrown"
name := "sbt-opinions"
version := "0.1.0-SNAPSHOT"

resolvers ++= Seq(
  Classpaths.typesafeReleases,
  Classpaths.sbtPluginReleases,
  "jgit-repo" at "http://download.eclipse.org/jgit/maven"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.1.6")
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.5.3")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")
addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % "0.3.2")
addSbtPlugin("org.brianmckenna" % "sbt-wartremover" % "0.13")
addSbtPlugin("org.scalastyle" % "scalastyle-sbt-plugin" % "0.6.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.0.4")
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.0.0")
addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.1.15")


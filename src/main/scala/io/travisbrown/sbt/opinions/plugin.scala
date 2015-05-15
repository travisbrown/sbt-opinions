package io.travisbrown.sbt.opinions

import com.typesafe.sbt.SbtGhPages.ghpages
import com.typesafe.sbt.SbtGit.git
import com.typesafe.sbt.SbtSite.SiteKeys.siteMappings
import com.typesafe.sbt.SbtSite.site
import java.net.URL
import sbt._, Keys._
import sbtunidoc.Plugin.{ScalaUnidoc, unidocSettings}
import scoverage.ScoverageSbtPlugin.ScoverageKeys
import wartremover.WartRemover.autoImport.{Wart, Warts, wartremoverWarnings}

object SbtOpinionsPlugin extends AutoPlugin {
  /**
   * The contents of this object will automatically be in scope in the build
   * configuration of projects that use this plugin.
   */
  object autoImport extends SbtOpinionsKeys {
    /**
     * Settings for projects that should not be published.
     */
    lazy val noPublish = Seq(
      publish := {},
      publishLocal := {}
    )

    /**
     * Standard test dependencies.
     */
    val testDependencies = Seq(
      "org.scalacheck" %% "scalacheck" % "1.12.2",
      "org.scalatest" %% "scalatest" % "2.2.4"
    )

    /**
     * A convenience method for specifying metadata for GitHub projects.
     */
    def gitHubProject(
      user: String,
      project: String,
      apiDocsPath: String = "docs"
    ): ProjectMetadata =
      ProjectMetadata(
        url(s"https://github.com/$user/$project"),
        url(s"https://$user.github.io/$project/$apiDocsPath"),
        url(s"https://github.com/$user/$project"),
        s"git@github.com:$user/$project.git",
        s"scm:git:git@github.com:$user/$project.git",
        apiDocsPath
      )

    /**
     * A convenience method for defining developers.
     */
    def developer(id: String, name: String, url: URL) = Developer(id, name, url)

    /**
     * Included for convenience (otherwise plugin users would have to import
     * this).
     */
    val coverageExcludedPackages = ScoverageKeys.coverageExcludedPackages
  }

  import autoImport._

  /**
   * Standard compiler options.
   */
  lazy val compilerOptions = Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-unchecked",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Xfuture"
  )

  /**
   * Standard project settings.
   */
  val baseSettings = Seq(
    libraryDependencies ++= testDependencies.map(_ % "test"),
    scalacOptions ++= compilerOptions ++ (
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 11)) => Seq("-Ywarn-unused-import")
        case _ => Nil
      }
    ),
    scalacOptions in (Compile, console) := compilerOptions,
    wartremoverWarnings in (Compile, compile) ++= Warts.allBut(
      Wart.NoNeedForMonad,
      Wart.Null,
      Wart.DefaultArguments
    )
  )

  /**
   * Standard publication settings.
   */
  lazy val publishSettings = Seq(
    publishMavenStyle := true,
    publishArtifact := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
     },
    publishArtifact in Test := false,
    licenses := Seq(
      "Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    homepage := Some(projectMetadata.value.homepageUrl),
    autoAPIMappings := true,
    apiURL := Some(projectMetadata.value.apiDocsUrl),
    scmInfo := Some(
      ScmInfo(projectMetadata.value.gitHttpUrl, projectMetadata.value.gitConnection)
    ),
    pomExtra :=
      <developers>
        {
          projectDevelopers.value.map {
            case Developer(id, name, url) =>
              <developer>
                <id>{id}</id>
                <name>{name}</name>
                <url>{url}</url>
              </developer>
          }
        }
      </developers>
  )

  override val trigger = allRequirements

  /**
   * Standard documentation settings.
   */
  lazy val docSettings = 
    site.settings ++ ghpages.settings ++ unidocSettings ++ Seq(
      siteMappings <++= (
        mappings in (ScalaUnidoc, packageDoc),
        projectMetadata
      ).map {
        case (ms, metadata) =>
          for { (f, d) <- ms } yield (f, metadata.apiDocsPath + "/" + d)
      },
      git.remoteRepo := projectMetadata.value.gitRepo
    )

  /**
   * Not sure why these need to be included here instead of in the project
   * settings, but they do.
   */
  override lazy val buildSettings = Seq(
    scalaVersion := "2.11.6",
    crossScalaVersions := Seq("2.10.5", "2.11.6")
  )

  /**
   * Settings that will be automatically added to each project.
   */
  override lazy val projectSettings =
    baseSettings ++ publishSettings ++ docSettings
}


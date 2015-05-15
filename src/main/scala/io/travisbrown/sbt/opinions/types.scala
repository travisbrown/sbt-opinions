package io.travisbrown.sbt.opinions

import java.net.URL

/**
 * Represents metadata needed to populate the published POM and perform other
 * tasks (such as pushing a project site to GitHub Pages).
 */
case class ProjectMetadata(
  homepageUrl: URL,
  apiDocsUrl: URL,
  gitHttpUrl: URL,
  gitRepo: String,
  gitConnection: String,
  apiDocsPath: String = "docs"
)

/**
 * Represents a person who will be included in the list of developers in the
 * published POM.
 */
case class Developer(id: String, name: String, url: URL)


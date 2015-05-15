package io.travisbrown.sbt.opinions

import sbt._, Keys._

/**
 * Plugin types and keys.
 *
 * These are defined separately from the `autoImport` object (and only mixed
 * in there) in order to make it easier for other plugins to refer to them.
 */
trait SbtOpinionsKeys {
  val projectMetadata = settingKey[ProjectMetadata](
    "Homepage URL and other essential information about the project"
  )

  val projectDevelopers = settingKey[Seq[Developer]](
    "Developers associated with the project"
  )
}

object SbtOpinionsKeys extends SbtOpinionsKeys


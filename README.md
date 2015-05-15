# sbt-opinions

[![Build Status](https://img.shields.io/travis/travisbrown/sbt-opinions/master.svg)](https://travis-ci.org/travisbrown/sbt-opinions)

This SBT plugin aggregates settings and other plugins that are standard across
[Finagle organization](https://github.com/finagle/) projects, allowing us to
avoid boilerplate in those projects' build definitions, to ensure consistency,
and to update versions in a single place.

The plugin is still a proof-of-concept with minimal functionality. See [this
commit](https://github.com/travisbrown/finch/commit/35a46f1a8f9fa52f445670425c63fc05e4679cab)
for an example of the kind of boilerplate elimination that is currently
supported.

## License

Licensed under the **[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)** (the "License");
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


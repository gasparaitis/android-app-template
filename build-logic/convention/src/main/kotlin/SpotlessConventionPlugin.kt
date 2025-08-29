/*
 * Copyright 2025 Replace With Your Name
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.diffplug.gradle.spotless.BaseKotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.spotless.extra.wtp.EclipseWtpFormatterStep
import com.example.template.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")
            val ktfmtVersion = project.libs.findVersion("ktfmt").get().toString()
            extensions.getByType(SpotlessExtension::class.java).apply {
                json {
                    target("**/*.json")
                    targetExclude("**/build/**/*.json")
                    gson().indentWithSpaces(2).sortByKeys()
                }
                format("html") {
                    target("**/*.html")
                    targetExclude("**/build/**/*.html")
                    prettier()
                        .config(mapOf("parser" to "html", "printWidth" to 80, "tabWidth" to 2))
                }
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt", "tools/spotless/*.kt")
                    applyKtfmt(ktfmtVersion)
                    licenseHeaderFile(
                        isolated.rootProject.projectDirectory.file("tools/spotless/copyright.kt")
                    )
                }
                /* TODO: Using format("kts") instead of `kotlinGradle` due to this issue: https://github.com/diffplug/spotless/issues/1956 */
                kotlinGradle { applyKtfmt(ktfmtVersion) }
                format("kts") {
                    target("**/*.kts")
                    targetExclude("**/build/**/*.kts", "tools/spotless/*.kts")
                    // Look for the first line that doesn't have a block comment (assumed to be the
                    // license)
                    licenseHeaderFile(
                        isolated.rootProject.projectDirectory.file("tools/spotless/copyright.kts"),
                        "(^(?![\\/ ]\\*).*$)",
                    )
                }
                format("markdown") {
                    target("**/*.md")
                    targetExclude("**/build/**/*.md")
                    prettier()
                        .config(
                            mapOf(
                                "parser" to "markdown",
                                "printWidth" to 80,
                                "proseWrap" to "always",
                            )
                        )
                }
                format("toml") {
                    target("**/*.toml")
                    targetExclude("**/build/**/*.toml")
                    prettier(mapOf("prettier-plugin-toml" to "0.3.5"))
                        .config(mapOf("parser" to "toml", "printWidth" to 80, "tabWidth" to 2))
                }
                format("xml") {
                    eclipseWtp(EclipseWtpFormatterStep.XML)
                    target("**/*.xml")
                    targetExclude("**/build/**/*.xml", "tools/spotless/*.xml", "**/.idea/**/*.xml")
                    // Look for the first XML tag that isn't a comment (<!--) or the xml declaration
                    // (<?xml)
                    licenseHeaderFile(
                        isolated.rootProject.projectDirectory.file("tools/spotless/copyright.xml"),
                        "(<[^!?])",
                    )
                }
                yaml {
                    target("**/*.yml", "**/*.yaml")
                    targetExclude("**/build/**/*.yml", "**/build/**/*.yaml")
                    prettier()
                        .config(mapOf("parser" to "yaml", "printWidth" to 80, "tabWidth" to 2))
                }
            }
        }
    }

    private fun BaseKotlinExtension.applyKtfmt(version: String) {
        ktfmt(version).kotlinlangStyle().configure { formattingOptions ->
            with(formattingOptions) {
                setMaxWidth(100)
                setBlockIndent(4)
                setContinuationIndent(4)
                setRemoveUnusedImports(true)
                setManageTrailingCommas(true)
            }
        }
    }
}

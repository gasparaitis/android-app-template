/*
 * Copyright 2025 Justas Gasparaitis
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
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt", "tools/spotless/*.kt")
                    applyKtfmt(ktfmtVersion)
                    licenseHeaderFile(
                        isolated.rootProject.projectDirectory.file("tools/spotless/copyright.kt")
                    )
                }
                kotlinGradle { applyKtfmt(ktfmtVersion) }

                // Not using `kotlinGradle` due to this issue:
                // https://github.com/diffplug/config/spotless/issues/1956
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
                format("xml") {
                    eclipseWtp(EclipseWtpFormatterStep.XML)
                    target("**/*.xml")
                    targetExclude("**/build/**/*.xml", "tools/spotless/*.xml")
                    // Look for the first XML tag that isn't a comment (<!--) or the xml declaration
                    // (<?xml)
                    licenseHeaderFile(
                        isolated.rootProject.projectDirectory.file("tools/spotless/copyright.xml"),
                        "(<[^!?])",
                    )
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

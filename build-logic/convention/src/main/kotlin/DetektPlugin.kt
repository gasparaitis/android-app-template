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
import com.example.template.convention.detektPlugins
import com.example.template.convention.libs
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) { apply("io.gitlab.arturbosch.detekt") }
            dependencies { detektPlugins(libs.findLibrary("detekt.compose").get()) }
            tasks.withType<Detekt>().configureEach {
                parallel = true
                config.setFrom(
                    files("${isolated.rootProject.projectDirectory}/config/detekt/detekt.yml")
                )
                include("**/*.kt")
                include("**/*.kts")
                exclude("**/resources/**")
                exclude("**/build/**")
                reports {
                    txt { required.set(false) }
                    sarif { required.set(true) }
                    xml { required.set(false) }
                    md { required.set(false) }
                    html { required.set(false) }
                }
            }
        }
    }
}

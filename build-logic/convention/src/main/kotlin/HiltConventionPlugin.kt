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
import com.android.build.gradle.BaseExtension
import com.example.convention.implementation
import com.example.convention.ksp
import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }
            dependencies { ksp(libs.findLibrary("hilt.compiler")) }

            // Add support for JVM Module, base on org.jetbrains.kotlin.jvm
            extensions.configure<KotlinJvmProjectExtension> {
                dependencies { implementation(libs.findLibrary("hilt.core")) }
            }

            // Add support for all Android modules
            extensions.configure<BaseExtension> {
                apply(plugin = "dagger.hilt.android.plugin")
                dependencies { implementation(libs.findLibrary("hilt.android")) }
            }
        }
    }
}

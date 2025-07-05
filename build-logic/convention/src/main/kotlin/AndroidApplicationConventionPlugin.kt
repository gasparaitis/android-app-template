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
import com.android.build.api.dsl.ApplicationExtension
import com.example.template.convention.configureAndroidCompose
import com.example.template.convention.configureAndroidKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
            apply(plugin = "com.google.android.gms.oss-licenses-plugin")
            apply(plugin = "com.dropbox.dependency-guard")
            apply(plugin = "convention.android.lint")
            apply(plugin = "convention.compose")
            apply(plugin = "convention.detekt")
            apply(plugin = "convention.hilt")
            extensions.configure<ApplicationExtension> {
                configureAndroidKotlin(this)
                configureAndroidCompose(this)
            }
        }
    }
}

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
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)
}

java {
    // FIXME: Use ProjectVersions.Java.JAVA_VERSION
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        // FIXME: Use ProjectVersions.Java.JVM_TARGET
        jvmTarget = JvmTarget.JVM_21
    }
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    implementation(libs.detekt.gradle.plugin)
    implementation(libs.ksp.gradle.plugin)
    implementation(libs.spotless.gradle.plugin)
    implementation(kotlin("stdlib"))
    lintChecks(libs.androidx.lint.gradle)
}

gradlePlugin {
    plugins {
        register("android.app") {
            id = libs.plugins.convention.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("android.compose.lib") {
            id = libs.plugins.convention.android.compose.library.get().pluginId
            implementationClass = "AndroidComposeLibraryConventionPlugin"
        }
        register("android.lib") {
            id = libs.plugins.convention.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("android.lint") {
            id = libs.plugins.convention.android.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("detekt") {
            id = libs.plugins.convention.detekt.get().pluginId
            implementationClass = "DetektPlugin"
        }
        register("hilt") {
            id = libs.plugins.convention.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("feature") {
            id = libs.plugins.convention.feature.get().pluginId
            implementationClass = "FeatureConventionPlugin"
        }
        register("data") {
            id = libs.plugins.convention.data.get().pluginId
            implementationClass = "DataConventionPlugin"
        }
        register("testing") {
            id = libs.plugins.convention.testing.get().pluginId
            implementationClass = "TestingConventionPlugin"
        }
        register("spotless") {
            id = libs.plugins.convention.spotless.get().pluginId
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}

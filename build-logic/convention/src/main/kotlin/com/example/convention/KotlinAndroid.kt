/*
 * Copyright 2022 Justas Gasparaitis
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
package com.example.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/** Configure base Kotlin with Android options */
internal fun Project.configureAndroidKotlin(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = ProjectVersions.Android.COMPILE_SDK
        defaultConfig.minSdk = ProjectVersions.Android.MIN_SDK
        compileOptions {
            sourceCompatibility = ProjectVersions.Java.JAVA_VERSION
            targetCompatibility = ProjectVersions.Java.JAVA_VERSION
        }
    }
    configureKotlin<KotlinAndroidProjectExtension>()
}

/** Configure base Kotlin options for JVM (non-Android) */
internal fun Project.configureJvmKotlin() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = ProjectVersions.Java.JAVA_VERSION
        targetCompatibility = ProjectVersions.Java.JAVA_VERSION
    }
    configureKotlin<KotlinJvmProjectExtension>()
}

/** Configure base Kotlin options */
private inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() =
    configure<T> {
        // Treat all Kotlin warnings as errors (disabled by default)
        // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
        val warningsAsErrors =
            providers.gradleProperty("warningsAsErrors").map { it.toBoolean() }.orElse(false)
        when (this) {
            is KotlinAndroidProjectExtension -> compilerOptions
            is KotlinJvmProjectExtension -> compilerOptions
            else -> TODO("Unsupported project extension $this ${T::class}")
        }.apply {
            jvmTarget.assign(ProjectVersions.Java.JVM_TARGET)
            allWarningsAsErrors.assign(warningsAsErrors)
            freeCompilerArgs.addAll(
                // Enable experimental Compose, Kotlin Coroutines, Serialization.
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
            )
        }
    }

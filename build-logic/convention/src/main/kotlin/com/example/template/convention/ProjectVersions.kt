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
package com.example.template.convention

import java.io.BufferedReader
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.support.useToRun
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object ProjectVersions {
    object Android {
        const val APP_DEBUG_APPLICATION_ID_SUFFIX = ".debug"
        const val APP_ID = "com.example.template"
        val APP_VERSION_CODE
            get() = gitCommitCount()

        const val APP_VERSION_NAME = "2025.6"
        val APP_VERSION_NAME_SUFFIX
            get() = "-$APP_VERSION_CODE:${gitBuildType()}"

        const val COMPILE_SDK = 36
        const val MIN_SDK = 21
        const val TARGET_SDK = COMPILE_SDK
    }

    object Java {
        val JVM_TARGET = JvmTarget.JVM_21
        val JAVA_VERSION = JavaVersion.VERSION_21
    }

    private fun gitBuildType(): String {
        val branch = executeCommand("git", "rev-parse", "--abbrev-ref", "HEAD")
        return if (branch == "main") "Release" else "QA"
    }

    private fun gitCommitCount(): Int = executeCommand("git", "rev-list", "--count", "HEAD").toInt()

    private fun executeCommand(vararg args: String): String {
        Runtime.getRuntime().exec(args).run {
            waitFor()
            val output = inputStream.useToRun { bufferedReader().use(BufferedReader::readText) }
            destroy()
            return output.trim()
        }
    }
}

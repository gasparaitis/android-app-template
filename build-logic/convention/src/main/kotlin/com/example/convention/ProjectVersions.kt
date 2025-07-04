package com.example.convention

import java.io.BufferedReader
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.support.useToRun
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object ProjectVersions {
    object Android {
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

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
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    implementation(libs.detekt.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    implementation(libs.spotless.gradle.plugin)
    lintChecks(libs.androidx.lint.gradle)
}

gradlePlugin {
    plugins {
        register("android.app") {
            id = libs.plugins.convention.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("android.lib") {
            id = libs.plugins.convention.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("compose") {
            id = libs.plugins.convention.compose.get().pluginId
            implementationClass = "ComposeConventionPlugin"
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

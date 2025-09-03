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
import com.example.template.convention.ProjectVersions

plugins {
    alias(libs.plugins.convention.android.application)
}

android {
    namespace = ProjectVersions.Android.APP_ID
    defaultConfig {
        applicationId = ProjectVersions.Android.APP_ID
        targetSdk = ProjectVersions.Android.TARGET_SDK
        versionCode = ProjectVersions.Android.APP_VERSION_CODE
        versionName = ProjectVersions.Android.APP_VERSION_NAME
    }
    buildTypes {
        all { versionNameSuffix = ProjectVersions.Android.APP_VERSION_NAME_SUFFIX }
        debug {
            applicationIdSuffix = ProjectVersions.Android.APP_DEBUG_APPLICATION_ID_SUFFIX
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":feature:settings"))
    implementation(libs.androidx.activity.compose)
}

dependencyGuard { configuration("releaseRuntimeClasspath") }

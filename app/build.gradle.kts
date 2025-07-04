import com.example.convention.ProjectVersions

plugins {
    alias(libs.plugins.convention.android.application)
}

android {
    namespace = ProjectVersions.Android.APP_ID
    defaultConfig {
        applicationId = ProjectVersions.Android.APP_ID
        versionCode = ProjectVersions.Android.APP_VERSION_CODE
        versionName = ProjectVersions.Android.APP_VERSION_NAME
    }
    buildTypes {
        all {
            versionNameSuffix = ProjectVersions.Android.APP_VERSION_NAME_SUFFIX
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileSdk = ProjectVersions.Android.COMPILE_SDK
    defaultConfig {
        minSdk = ProjectVersions.Android.MIN_SDK
        targetSdk = ProjectVersions.Android.TARGET_SDK
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.google.oss.licenses)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

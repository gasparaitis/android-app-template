import com.example.template.convention.ProjectVersions

plugins {
    alias(libs.plugins.convention.feature)
}

android {
    namespace = ProjectVersions.Android.APP_ID.plus(".feature.settings")
}

dependencies {
    implementation(libs.google.oss.licenses)
}

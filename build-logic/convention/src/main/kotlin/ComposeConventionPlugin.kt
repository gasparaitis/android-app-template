import com.android.build.gradle.BaseExtension
import com.example.convention.androidTestImplementation
import com.example.convention.implementation
import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) { apply("org.jetbrains.kotlin.plugin.compose") }
            extensions.configure<BaseExtension> {
                buildFeatures.apply { compose = true }
                dependencies {
                    val bom = libs.findLibrary("compose.bom")
                    implementation(platform(bom))
                    androidTestImplementation(platform(bom))
                }
            }
        }
    }
}

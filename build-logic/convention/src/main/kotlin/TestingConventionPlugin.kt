import com.example.convention.libs
import com.example.convention.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class TestingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }
            dependencies {
                testImplementation(kotlin("test"))
                testImplementation(libs.findLibrary("coroutines.test"))
                testImplementation(libs.findLibrary("junit"))
            }
        }
    }
}

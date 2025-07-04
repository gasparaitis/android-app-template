import com.android.build.gradle.BaseExtension
import com.example.convention.implementation
import com.example.convention.ksp
import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }
            dependencies {
                ksp(libs.findLibrary("hilt.compiler"))
            }

            // Add support for JVM Module, base on org.jetbrains.kotlin.jvm
            extensions.configure<KotlinJvmProjectExtension> {
                dependencies {
                    implementation(libs.findLibrary("hilt.core"))
                }
            }

            // Add support for all Android modules
            extensions.configure<BaseExtension> {
                apply(plugin = "dagger.hilt.android.plugin")
                dependencies {
                    implementation(libs.findLibrary("hilt.android"))
                }
            }
        }
    }
}

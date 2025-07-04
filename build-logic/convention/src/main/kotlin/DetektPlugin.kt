import com.example.convention.detektPlugins
import com.example.convention.libs
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.gitlab.arturbosch.detekt")
            }

            dependencies {
                detektPlugins(libs.findLibrary("detekt.compose"))
            }

            tasks.withType<Detekt> {
                parallel = true
                config.setFrom(files("${rootProject.rootDir}/.detekt/detekt.yml"))
                include("**/*.kt")
                include("**/*.kts")
                exclude("**/resources/**")
                exclude("**/build/**")
                reports {
                    txt {
                        required.set(true)
                    }
                    sarif {
                        required.set(false)
                    }
                    xml {
                        required.set(false)
                    }
                    md {
                        required.set(false)
                    }
                    html {
                        required.set(false)
                    }
                }
            }
        }
    }
}

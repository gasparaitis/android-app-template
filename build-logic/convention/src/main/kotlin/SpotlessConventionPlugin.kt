import com.diffplug.gradle.spotless.BaseKotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")
            val ktfmtVersion = project.libs.findVersion("ktfmt").get().toString()
            extensions.getByType(SpotlessExtension::class.java).apply {
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt")
                    applyKtfmt(ktfmtVersion)
                }
                kotlinGradle {
                    target("*.gradle.kts")
                    applyKtfmt(ktfmtVersion)
                }
            }
        }
    }

    private fun BaseKotlinExtension.applyKtfmt(version: String) {
        ktfmt(version).kotlinlangStyle().configure { formattingOptions ->
            with(formattingOptions) {
                setMaxWidth(100)
                setBlockIndent(4)
                setContinuationIndent(4)
                setRemoveUnusedImports(false)
                setManageTrailingCommas(false)
            }
        }
    }
}

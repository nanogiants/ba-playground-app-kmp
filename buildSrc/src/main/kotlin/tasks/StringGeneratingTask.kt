package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import tasks.TwineStringGeneratingTask.TwineFormat.ANDROID
import tasks.TwineStringGeneratingTask.TwineFormat.IOS

open class TwineStringGeneratingTask : DefaultTask() {

  enum class TwineFormat(val value: String) {
    ANDROID("android"),
    IOS("apple")
  }

  private var totalScript: String = ""

  @Input
  var localizationFilePath: String = ""

  @Input
  var androidProjectRoot: String = ""

  @Input
  var androidSubProjects: List<String> = listOf()

  @Input
  var iosProjectRoot: String = ""

  @TaskAction
  fun generateProjectStrings() {
    appendIosScript()
    appendAndroidScript()
    executeTotalScript()
  }

  private fun appendIosScript() {
    totalScript += generateTwineCommand(
      localizationFilePath,
      "$iosProjectRoot/Playground/Resources",
      IOS
    )
  }

  private fun appendAndroidScript() {
    androidSubProjects.forEach { moduleName ->
      totalScript += generateTwineCommand(
        localizationFilePath,
        "$androidProjectRoot/$moduleName/src/main/res",
        ANDROID,
        moduleName
      )
    }
  }

  private fun generateTwineCommand(
    source: String,
    destination: String,
    format: TwineFormat,
    tagName: String = ""
  ): String {
    val tag = if (tagName.isEmpty()) "" else "--tags $tagName"
    return "twine generate-all-localization-files $source $destination $tag --validate --format ${format.value}; "
  }

  private fun executeTotalScript() {
    val script = "if hash twine 2>/dev/null; then $totalScript fi"
    project.exec {
      executable = "sh"
      args = listOf("-c", script)
    }
  }
}


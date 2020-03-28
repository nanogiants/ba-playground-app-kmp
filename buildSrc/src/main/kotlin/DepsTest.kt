object TestVersions {
  const val junit = "4.12"
  const val runner = "1.2.0"
  const val espressoCore = "3.2.0"
  const val kotlinTest = KotlinConstants.kotlinVersion
}

object DepsTest {

  const val junit = "junit:junit:${TestVersions.junit}"
  const val runner = "androidx.test:runner:${TestVersions.runner}"
  const val espressoCore = "androidx.test.espresso:espresso-core:${TestVersions.espressoCore}"

  object KotlinTest {
    const val common = "org.jetbrains.kotlin:kotlin-test-common:${TestVersions.kotlinTest}"
    const val annotationsCommon =
      "org.jetbrains.kotlin:kotlin-test-annotations-common:${TestVersions.kotlinTest}"
    const val test = "org.jetbrains.kotlin:kotlin-test:${TestVersions.kotlinTest}"
    const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${TestVersions.kotlinTest}"
  }
}
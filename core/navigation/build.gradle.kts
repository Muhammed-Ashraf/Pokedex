plugins {
  id("example.pokedex.android.library")
  id("example.pokedex.android.library.compose")
  alias(libs.plugins.kotlinx.serialization)
  id("example.pokedex.android.hilt")
  id("example.pokedex.spotless")
}

android {
  namespace = "com.example.pokedex.compose.core.navigation"
}

dependencies {
  implementation(projects.core.model)

  implementation(libs.androidx.core)
  implementation(libs.kotlinx.coroutines.android)

  api(libs.androidx.navigation.compose)

  // json parsing
  implementation(libs.kotlinx.serialization.json)
}
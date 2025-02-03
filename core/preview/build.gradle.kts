plugins {
  id("example.pokedex.android.library")
  id("example.pokedex.android.library.compose")
  id("example.pokedex.android.hilt")
  id("example.pokedex.spotless")
}

android {
  namespace = "com.example.pokedex.compose.feature.preview"
}

dependencies {
  // core
  implementation(projects.core.designsystem)
  implementation(projects.core.navigation)
  implementation(projects.core.model)
}
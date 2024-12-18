plugins {
  id("example.pokedex.android.library")
  id("example.pokedex.android.library.compose")
  id("example.pokedex.spotless")
}

android {
  namespace = "com.example.pokedex.compose.designsystem"
}

dependencies {
  // image loading
  api(libs.landscapist.glide)
  api(libs.landscapist.animation)
  api(libs.landscapist.placeholder)
  api(libs.landscapist.palette)

  api(libs.androidx.compose.runtime)
  api(libs.androidx.compose.ui)
  api(libs.androidx.compose.ui.tooling)
  api(libs.androidx.compose.ui.tooling.preview)
  api(libs.androidx.compose.animation)
  api(libs.androidx.compose.material3)
  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.foundation.layout)
}

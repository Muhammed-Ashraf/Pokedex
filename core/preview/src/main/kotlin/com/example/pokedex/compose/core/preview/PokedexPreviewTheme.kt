package com.example.pokedex.compose.core.preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.pokedex.compose.core.designsystem.theme.PokedexTheme
import com.example.pokedex.compose.core.navigation.LocalComposeNavigator
import com.example.pokedex.compose.core.navigation.PokedexComposeNavigator

@Composable
fun PokedexPreviewTheme(
  // Lambda function that allows animated UI elements within a shared transition scope
  content: @Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit,
) {
  // Provides a local instance of the Compose Navigator to the composition tree.
  // This allows navigation-related dependencies to be accessed within the composable hierarchy.
  CompositionLocalProvider(
    LocalComposeNavigator provides PokedexComposeNavigator(), // Injects a custom navigator instance
  ) {
    // Applies the Pokedex app's theme to ensure consistent styling
    PokedexTheme {
      // Defines a scope for shared element transitions (animations between screens)
      SharedTransitionScope {
        // Makes the content visible with an animation
        // `AnimatedVisibility` ensures smooth appearance and disappearance effects
        AnimatedVisibility(visible = true, label = "") {
          // Calls the lambda function `content`, passing the current `AnimatedVisibilityScope`
          // This allows the caller to define UI elements inside this animated visibility scope
          content(this)
        }
      }
    }
  }
}
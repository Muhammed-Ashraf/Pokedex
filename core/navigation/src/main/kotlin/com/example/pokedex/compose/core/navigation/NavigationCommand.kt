package com.example.pokedex.compose.core.navigation

import androidx.navigation.NavOptions

// A sealed interface to represent navigation commands.
// Sealed interfaces allow defining a restricted hierarchy of types that implement this interface.
// All implementations must be declared in the same file, ensuring type safety.
sealed interface NavigationCommand {

  // A singleton object representing a command to navigate up the navigation stack.
  // "NavigateUp" typically means going back to the previous screen or destination.
  data object NavigateUp : NavigationCommand
}

// A specialized sealed interface that extends `NavigationCommand` for commands
// related to Jetpack Compose navigation.
sealed interface ComposeNavigationCommand : NavigationCommand {

  // A command to navigate to a specific route in the Compose navigation system.
  // The route is represented as a generic type `T`, allowing flexibility in how routes are defined.
  data class NavigateToRoute<T : Any>(
    val route: T,                 // The destination route (e.g., a route name or identifier).
    val options: NavOptions? = null // Optional navigation options, such as animations or pop behaviors.
  ) : ComposeNavigationCommand

  // A command to navigate "up" the navigation stack while passing a result back
  // to the previous destination or consumer.
  data class NavigateUpWithResult<R, T : Any>(
    val key: String,       // A unique key to identify the result and associate it with a consumer.
    val result: R,         // The result to be passed back to the previous destination.
    val route: T? = null   // An optional specific route to navigate to after navigating up.
  ) : ComposeNavigationCommand

  // A command to "pop up" to a specific route in the navigation stack.
  // This clears intermediate destinations in the stack.
  data class PopUpToRoute<T : Any>(
    val route: T,            // The route to pop up to in the navigation stack.
    val inclusive: Boolean   // Whether the specified route itself should also be cleared (true) or retained (false).
  ) : ComposeNavigationCommand
}

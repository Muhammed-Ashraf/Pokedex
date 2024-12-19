package com.example.pokedex.compose.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onSubscription

// Base Navigator class to manage navigation commands and the NavController
abstract class Navigator {
  // Shared flow to emit navigation commands like navigating up or to a route
  val navigationCommands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

  // A StateFlow to hold the NavController, allowing ViewModels to observe navigation state
  val navControllerFlow = MutableStateFlow<NavController?>(null)

  // Function to emit a command to navigate up (move back in the navigation stack)
  fun navigateUp() {
    navigationCommands.tryEmit(NavigationCommand.NavigateUp)
  }
}

// AppComposeNavigator class for managing navigation specifically in Jetpack Compose-based apps
abstract class AppComposeNavigator<T : Any> : Navigator() {

  // Abstract method to navigate to a specific route, taking optional navigation options
  abstract fun navigate(route: T, optionsBuilder: (NavOptionsBuilder.() -> Unit)? = null)

  // Abstract method to navigate back while passing a result, identified by a key
  abstract fun <R> navigateBackWithResult(key: String, result: R, route: T?)

  // Abstract method to pop back the navigation stack up to a specific route
  abstract fun popUpTo(route: T, inclusive: Boolean)

  // Abstract method to navigate to a route and clear the back stack
  abstract fun navigateAndClearBackStack(route: T)

  // Function to handle navigation commands and perform actions on the NavController
  suspend fun handleNavigationCommands(navController: NavController) {
    navigationCommands
      // Sets the current NavController when the flow subscription starts
      .onSubscription { this@AppComposeNavigator.navControllerFlow.value = navController }
      // Clears the NavController when the flow completes
      .onCompletion { this@AppComposeNavigator.navControllerFlow.value = null }
      // Collects emitted navigation commands and executes them
      .collect { navController.handleComposeNavigationCommand(it) }
  }

  // Extension function to handle navigation commands for Compose-based navigation
  private fun NavController.handleComposeNavigationCommand(navigationCommand: NavigationCommand) {
    when (navigationCommand) {
      // For NavigateToRoute command, navigate to the specified route
      is ComposeNavigationCommand.NavigateToRoute<*> -> {
        navigate(navigationCommand.route, navigationCommand.options)
      }

      // For NavigateUp command, move back in the navigation stack
      NavigationCommand.NavigateUp -> navigateUp()

      // For PopUpToRoute command, pop the back stack to the specified route
      is ComposeNavigationCommand.PopUpToRoute<*> -> popBackStack(
        navigationCommand.route,
        navigationCommand.inclusive,
      )

      // For NavigateUpWithResult command, pass the result back to the previous route
      is ComposeNavigationCommand.NavigateUpWithResult<*, *> -> {
        navUpWithResult(navigationCommand)
      }
    }
  }

  // Function to handle navigating up with a result and passing the result to the back stack entry
  private fun NavController.navUpWithResult(
    navigationCommand: ComposeNavigationCommand.NavigateUpWithResult<*, *>,
  ) {
    // Get the back stack entry associated with the route, or use the previous back stack entry
    val backStackEntry =
      navigationCommand.route?.let { getBackStackEntry(it) }
        ?: previousBackStackEntry

    // Save the result in the back stack entry's saved state handle using the specified key
    backStackEntry?.savedStateHandle?.set(
      navigationCommand.key,
      navigationCommand.result,
    )

    // If a route is provided, pop the back stack to that route
    navigationCommand.route?.let {
      popBackStack(it, false)
    } ?: navigateUp() // If no route is provided, just navigate up
  }
}

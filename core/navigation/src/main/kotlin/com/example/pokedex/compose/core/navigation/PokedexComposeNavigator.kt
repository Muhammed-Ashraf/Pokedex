package com.example.pokedex.compose.core.navigation

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import javax.inject.Inject

/**
 * PokedexComposeNavigator is responsible for handling navigation commands within a Compose-based
 * Android application, specifically targeting navigation related to the PokedexScreen.
 * This class extends AppComposeNavigator<PokedexScreen> to work with a specific screen route type
 * (PokedexScreen), and it emits navigation commands to trigger navigation actions in the app's UI.
 */
class PokedexComposeNavigator @Inject constructor() : AppComposeNavigator<PokedexScreen>() {

  /**
   * Navigate to the specified PokedexScreen route, with optional navigation options.
   *
   * @param route The target PokedexScreen route to navigate to.
   * @param optionsBuilder A lambda that allows for optional customization of the navigation options,
   *                       such as animations, transitions, or back stack behaviors.
   *                       This is passed to navOptions to create the options if provided.
   */
  override fun navigate(route: PokedexScreen, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
    // If the optionsBuilder is provided, use it to create navOptions.
    val options = optionsBuilder?.let { navOptions(it) }
    
    // Emit the navigation command to navigate to the specified route, with the given options.
    navigationCommands.tryEmit(ComposeNavigationCommand.NavigateToRoute(route, options))
  }

  /**
   * Navigate to the specified PokedexScreen route and clear the back stack.
   *
   * @param route The target PokedexScreen route to navigate to.
   * This method clears the entire back stack by using the popUpTo(0) option,
   * ensuring that no previous destinations are retained in the back stack.
   */
  override fun navigateAndClearBackStack(route: PokedexScreen) {
    // Emit the navigation command to navigate to the given route, while clearing the back stack.
    navigationCommands.tryEmit(
      ComposeNavigationCommand.NavigateToRoute(
        route,
        navOptions {
          // popUpTo(0) clears the back stack, removing all previous destinations.
          popUpTo(0)
        },
      ),
    )
  }

  /**
   * Pop the back stack up to a specific route.
   *
   * @param route The route to pop up to in the back stack.
   * @param inclusive If true, the destination route will be removed as well; 
   *                  if false, it will remain in the back stack.
   */
  override fun popUpTo(route: PokedexScreen, inclusive: Boolean) {
    // Emit the navigation command to pop the back stack to the specified route.
    navigationCommands.tryEmit(ComposeNavigationCommand.PopUpToRoute(route, inclusive))
  }

  /**
   * Navigate back to the previous screen, passing a result back with the specified key.
   *
   * @param key The key used to identify the result.
   * @param result The result data to pass back.
   * @param route An optional route to specify the destination to navigate back to.
   * This method allows passing data back to the previous screen in the navigation flow.
   */
  override fun <R> navigateBackWithResult(key: String, result: R, route: PokedexScreen?) {
    // Emit the navigation command to navigate back, carrying the result data with the given key.
    navigationCommands.tryEmit(
      ComposeNavigationCommand.NavigateUpWithResult(
        key = key,
        result = result,
        route = route,
      ),
    )
  }
}

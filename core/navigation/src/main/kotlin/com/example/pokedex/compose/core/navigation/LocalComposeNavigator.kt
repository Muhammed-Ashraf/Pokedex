package com.example.pokedex.compose.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

/**
 * A CompositionLocal that holds the current instance of the AppComposeNavigator<PokedexScreen>.
 * 
 * CompositionLocal is a mechanism in Jetpack Compose for passing data down the composable hierarchy.
 * It allows us to "provide" a value at a higher level in the UI tree and "consume" it at a lower level,
 * making it accessible without explicitly passing it through every composable function.
 * 
 * The LocalComposeNavigator is a ProvidableCompositionLocal that holds the current instance of the 
 * AppComposeNavigator for navigating within the PokedexScreen flow. 
 * 
 * If this is accessed without a provided value (i.e., if no AppComposeNavigator is provided in the
 * current composable hierarchy), an error is thrown. The error message instructs the developer to
 * ensure that all usages of Pokedex components are wrapped in PokedexTheme, where the 
 * AppComposeNavigator is properly provided.
 */
public val LocalComposeNavigator: ProvidableCompositionLocal<AppComposeNavigator<PokedexScreen>> =
  compositionLocalOf {
    error(
      "No AppComposeNavigator provided! " +
        "Make sure to wrap all usages of Pokedex components in PokedexTheme.",
    )
  }

/**
 * Retrieves the current instance of the AppComposeNavigator<PokedexScreen> from the composition
 * context at the call site's position in the hierarchy.
 *
 * This property is marked with @Composable to indicate that it can be used in composable functions,
 * and it is marked with @ReadOnlyComposable to indicate that the value of the navigator should
 * be treated as immutable (read-only) when accessed.
 *
 * The currentComposeNavigator function uses LocalComposeNavigator.current to access the current 
 * instance of AppComposeNavigator, which has been provided in the composable hierarchy using 
 * CompositionLocalProvider. If no instance of AppComposeNavigator is provided, it will trigger
 * an error as defined in LocalComposeNavigator.
 */
public val currentComposeNavigator: AppComposeNavigator<PokedexScreen>
  @Composable
  @ReadOnlyComposable
  get() = LocalComposeNavigator.current

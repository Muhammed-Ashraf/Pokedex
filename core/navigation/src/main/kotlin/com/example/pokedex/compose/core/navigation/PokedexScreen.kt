package com.example.pokedex.compose.core.navigation

import com.example.pokedex.compose.core.model.Pokemon
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

// Sealed interface representing different screens in the Pokedex app.
sealed interface PokedexScreen {
  
  // A screen representing the Home page of the Pokedex app.
  @Serializable // Marks this class as serializable for Kotlin Serialization.
  data object Home : PokedexScreen

  // A screen representing the Details page, which displays information about a specific Pokemon.
  @Serializable // Marks this class as serializable for Kotlin Serialization.
  data class Details(val pokemon: Pokemon) : PokedexScreen {
    
    // Companion object inside the Details class to provide a type mapping.
    companion object {
      // A map associating the type of `Pokemon` to a custom NavType (PokemonType),
      // which helps in navigating and serializing the Pokemon object.
      val typeMap = mapOf(typeOf<Pokemon>() to PokemonType)
    }
  }
}

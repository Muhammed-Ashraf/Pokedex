package com.example.pokedex.compose.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.example.pokedex.compose.core.model.Pokemon
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Custom NavType for handling Pokemon objects in Android's Navigation system.
object PokemonType : NavType<Pokemon>(isNullableAllowed = false) {

  /**
   * This method puts a `Pokemon` object into a `Bundle` so that it can be passed between fragments or destinations.
   * It stores the `Pokemon` object as a Parcelable, allowing it to be passed safely within Android's Navigation system.
   * 
   * @param bundle The bundle where the Pokemon object will be stored.
   * @param key The key used to retrieve the object from the bundle.
   * @param value The Pokemon object to be stored.
   */
  override fun put(bundle: Bundle, key: String, value: Pokemon) {
    // Storing the Pokemon object as a Parcelable in the bundle
    bundle.putParcelable(key, value)
  }

  /**
   * This method retrieves a `Pokemon` object from a `Bundle` based on a given key.
   * It uses `BundleCompat.getParcelable` to safely retrieve the object from the bundle.
   * 
   * @param bundle The bundle that contains the Pokemon object.
   * @param key The key used to retrieve the Pokemon object from the bundle.
   * @return The retrieved Pokemon object, or null if not found.
   */
  override fun get(bundle: Bundle, key: String): Pokemon? =
    BundleCompat.getParcelable(bundle, key, Pokemon::class.java)

  /**
   * This method converts a `String` (usually passed via URL or deep link) into a `Pokemon` object.
   * The string is first URL-decoded and then deserialized using Kotlin Serialization.
   * 
   * @param value The string value that represents the serialized Pokemon object.
   * @return The deserialized Pokemon object.
   */
  override fun parseValue(value: String): Pokemon {
    // Decoding the value from URL-safe encoding and then deserializing it into a Pokemon object
    return Json.decodeFromString(Uri.decode(value))
  }

  /**
   * This method converts a `Pokemon` object into a URL-safe `String` so that it can be passed through navigation.
   * The Pokemon object is first serialized into JSON using Kotlin Serialization and then encoded to be URL-safe.
   * 
   * @param value The Pokemon object to be serialized.
   * @return A URL-safe string representation of the Pokemon object.
   */
  override fun serializeAsValue(value: Pokemon): String = Uri.encode(Json.encodeToString(value))
}

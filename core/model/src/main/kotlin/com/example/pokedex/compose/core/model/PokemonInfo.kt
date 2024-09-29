/*
 * Designed and developed by 2024 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.pokedex.compose.core.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

/*
 * The PokemonInfo class represents detailed information about a Pokémon.
 * It is annotated with @Serializable for Kotlin Serialization, allowing
 * it to be easily serialized/deserialized from formats such as JSON.
 * It contains various properties like ID, name, height, weight, experience,
 * types, and stats. Helper functions and lazy properties are used to format
 * and calculate values dynamically.
 */
@Immutable
@Serializable
data class PokemonInfo(
  /* The unique ID of the Pokémon, represented as an integer */
  @SerialName(value = "id")
  val id: Int,

  /* The name of the Pokémon, represented as a string */
  @SerialName(value = "name") val name: String,

  /* The height of the Pokémon in decimetres */
  @SerialName(value = "height") val height: Int,

  /* The weight of the Pokémon in hectograms */
  @SerialName(value = "weight") val weight: Int,

  /* The base experience the Pokémon gives when defeated */
  @SerialName(value = "base_experience") val experience: Int,

  /* A list representing the types of the Pokémon (e.g., Fire, Water) */
  @SerialName(value = "types") val types: List<TypeResponse>,

  /* A list representing the stats of the Pokémon (e.g., HP, Attack, Defense) */
  @SerialName(value = "stats") val stats: List<StatsResponse>,

  /* A randomly generated experience value, initialized to a random number between 0 and MAX_EXP (1000) */
  val exp: Int = Random.nextInt(MAX_EXP),
) {
  /*
   * Lazy properties for stats such as HP, Attack, Defense, and Speed.
   * These values are calculated when accessed for the first time.
   * If the corresponding stat (like "hp") is not found in the stats list,
   * a random value is generated as a fallback.
   */
  val hp: Int by lazy {
    stats.firstOrNull { it.stat.name == "hp" }?.baseStat ?: Random.nextInt(MAX_HP)
  }

  val attack: Int by lazy {
    stats.firstOrNull { it.stat.name == "attack" }?.baseStat ?: Random.nextInt(MAX_ATTACK)
  }

  val defense: Int by lazy {
    stats.firstOrNull { it.stat.name == "defense" }?.baseStat ?: Random.nextInt(MAX_DEFENSE)
  }

  val speed: Int by lazy {
    stats.firstOrNull { it.stat.name == "speed" }?.baseStat ?: Random.nextInt(MAX_SPEED)
  }

  /*
   * Helper function to return the Pokémon's ID as a formatted string in the format "#001"
   */
  fun getIdString(): String = String.format("#%03d", id)

  /*
   * Helper function to return the Pokémon's weight formatted as kilograms with one decimal place
   */
  fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)

  /*
   * Helper function to return the Pokémon's height formatted as meters with one decimal place
   */
  fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)

  /*
   * Helper function to return the Pokémon's HP formatted as "current_hp/MAX_HP"
   */
  fun getHpString(): String = " $hp/$MAX_HP"

  /*
   * Helper function to return the Pokémon's attack stat formatted as "current_attack/MAX_ATTACK"
   */
  fun getAttackString(): String = " $attack/$MAX_ATTACK"

  /*
   * Helper function to return the Pokémon's defense stat formatted as "current_defense/MAX_DEFENSE"
   */
  fun getDefenseString(): String = " $defense/$MAX_DEFENSE"

  /*
   * Helper function to return the Pokémon's speed stat formatted as "current_speed/MAX_SPEED"
   */
  fun getSpeedString(): String = " $speed/$MAX_SPEED"

  /*
   * Helper function to return the Pokémon's experience formatted as "current_exp/MAX_EXP"
   */
  fun getExpString(): String = " $exp/$MAX_EXP"

  /*
   * Represents the Pokémon's type response. Each Pokémon can have one or more types (e.g., Fire, Water).
   * The `slot` indicates whether it's the primary or secondary type, and the `type` represents the name of the type.
   */
  @Serializable
  data class TypeResponse(
    /* The slot or position of the type, for primary or secondary types */
    @SerialName(value = "slot") val slot: Int,

    /* The type object containing the name of the type (e.g., Fire, Water) */
    @SerialName(value = "type") val type: Type,
  )

  /*
   * Represents a Pokémon's stat (e.g., HP, Attack, Defense).
   * It includes the base stat, the effort stat (for how much effort the Pokémon can invest in the stat),
   * and the stat name itself.
   */
  @Serializable
  data class StatsResponse(
    /* The base value of the stat (e.g., base HP, base Attack) */
    @SerialName(value = "base_stat") val baseStat: Int,

    /* The effort stat, representing how much effort can be put into this stat */
    @SerialName(value = "effort") val effort: Int,

    /* The stat object, representing the name of the stat (e.g., hp, attack, defense) */
    @SerialName(value = "stat") val stat: Stat,
  )

  /*
   * Represents a stat's name (e.g., "hp", "attack", "defense").
   */
  @Serializable
  data class Stat(
    /* The name of the stat, which can be "hp", "attack", "defense", "speed", etc. */
    @SerialName(value = "name") val name: String,
  )

  /*
   * Represents a Pokémon's type name (e.g., "fire", "water").
   */
  @Serializable
  data class Type(
    /* The name of the type, which can be "fire", "water", "grass", etc. */
    @SerialName(value = "name") val name: String,
  )

  /*
   * Companion object holding constants for maximum values of Pokémon stats and experience.
   * These constants are used for validation and formatting purposes.
   */
  companion object {
    const val MAX_HP = 300          // Maximum HP for a Pokémon
    const val MAX_ATTACK = 300      // Maximum Attack stat for a Pokémon
    const val MAX_DEFENSE = 300     // Maximum Defense stat for a Pokémon
    const val MAX_SPEED = 300       // Maximum Speed stat for a Pokémon
    const val MAX_EXP = 1000        // Maximum Experience Points for a Pokémon
  }
}

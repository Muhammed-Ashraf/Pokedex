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

package com.example.pokedex.compose.core.preview

import com.example.pokedex.compose.core.model.Pokemon
import com.example.pokedex.compose.core.model.PokemonInfo


object PreviewUtils {

  fun mockPokemon() = Pokemon(
    page = 0,
    nameField = "bulbasaur",
    url = "https://pokeapi.co/api/v2/pokemon/1/",
  )

  fun mockPokemonList() = List(10) {
    Pokemon(page = 0, nameField = "bulbasaur$it", url = "")
  }

  fun mockPokemonInfo() = PokemonInfo(
    id = 1,
    name = "bulbasaur",
    height = 7,
    weight = 69,
    experience = 60,
    types = listOf(
      PokemonInfo.TypeResponse(slot = 0, type = PokemonInfo.Type("grass")),
      PokemonInfo.TypeResponse(slot = 0, type = PokemonInfo.Type("poison")),
    ),
    stats = listOf(
      PokemonInfo.StatsResponse(baseStat = 20, effort = 0, stat = PokemonInfo.Stat("hp")),
      PokemonInfo.StatsResponse(baseStat = 40, effort = 0, stat = PokemonInfo.Stat("attack")),
      PokemonInfo.StatsResponse(baseStat = 60, effort = 0, stat = PokemonInfo.Stat("defense")),
      PokemonInfo.StatsResponse(baseStat = 80, effort = 0, stat = PokemonInfo.Stat("attack")),
    ),
  )
}

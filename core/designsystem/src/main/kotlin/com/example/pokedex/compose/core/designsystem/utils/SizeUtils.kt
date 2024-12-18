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

package com.example.pokedex.compose.core.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

/**
 * Extension function for the Int type that converts a pixel value (px) to density-independent pixels (dp).
 *
 * This function uses Jetpack Compose's `LocalDensity` to access the current screen density and
 * performs the conversion based on that. Since it relies on `LocalDensity`, this function can
 * only be used within a Composable context.
 *
 * **How It Works:**
 * - The `LocalDensity.current` provides the screen's density in the current Composable tree.
 * - The `toDp()` method (provided by `Density`) converts the pixel value into dp using the formula:
 *   dp = px / density.
 *
 * **Usage Example:**
 * ```kotlin
 * val pixels = 16
 * val dpValue = pixels.pxToDp()
 * println(dpValue)  // Outputs: 8.dp on a screen with density = 2.0
 * ```
 *
 * **Why It's Marked @Composable:**
 * - It depends on `LocalDensity`, which is a CompositionLocal only available within Composable functions.
 *
 * Note: This function is not suitable for usage outside of a Composable context.
 *
 * @return A value in dp corresponding to the pixel value.
 */
@Composable
fun Int.pxToDp(): Dp = with(LocalDensity.current) { this@pxToDp.toDp() }


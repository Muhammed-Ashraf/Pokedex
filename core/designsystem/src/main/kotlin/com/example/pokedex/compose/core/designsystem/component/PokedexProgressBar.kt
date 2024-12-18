package com.example.pokedex.compose.core.designsystem.component

import android.content.res.Configuration
import androidx.annotation.FloatRange
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.compose.core.designsystem.theme.PokedexTheme
import com.example.pokedex.compose.core.designsystem.utils.pxToDp

@Composable
fun PokedexProgressBar(
  modifier: Modifier = Modifier, // Allows customization of layout and appearance, e.g., size, padding
  @FloatRange(0.0, 1.0) progress: Float, // Represents the progress percentage (between 0.0 and 1.0)
  color: Color, // Specifies the color of the progress bar
  label: String, // Text displayed alongside the progress bar, e.g., "150/300"
) {
  // Get the screen width in dp using LocalConfiguration
  val screenWidth = LocalConfiguration.current.screenWidthDp.dp.value

  // Check if the app is in preview/inspection mode
  val isLocalInspectionMode = LocalInspectionMode.current

  // Mutable state for the width of the progress bar based on the progress
  var progressWidth by remember {
    mutableFloatStateOf(
      if (isLocalInspectionMode) {
        screenWidth // If in inspection mode, set width to the screen width for previews
      } else {
        0f // Otherwise, initialize to 0
      },
    )
  }

  // Outer Box that serves as the container for the progress bar
  Box(
    modifier = modifier
      .fillMaxWidth() // Makes the container fill the available width
      .height(18.dp) // Sets the height of the progress bar
      .onSizeChanged { progressWidth = it.width * progress } // Dynamically update progress width when size changes
      .background(
        color = PokedexTheme.colors.absoluteWhite, // Background color of the bar (white in theme)
        shape = RoundedCornerShape(64.dp), // Rounded corners for aesthetic
      )
      .clip(RoundedCornerShape(64.dp)), // Ensure child content respects the rounded corners
  ) {
    // State to hold the width of the label text
    var textWidth by remember { mutableIntStateOf(0) }

    // A threshold value used to calculate padding or spacing for the label
    val threshold = 16

    // Determine if the label should be placed inside or outside the progress bar
    val isInner by remember(
      progressWidth, // Depends on the width of the progress bar
      textWidth, // And the width of the label text
    ) { mutableStateOf(progressWidth > (textWidth + threshold * 2)) }

    // Animation for the progress bar width to make it grow smoothly
    val animation: Float by animateFloatAsState(
      targetValue = if (progressWidth == 0f) 0f else 1f, // Animate from 0 to full width
      animationSpec = tween(durationMillis = 950, easing = LinearOutSlowInEasing), // Smooth animation with easing
      label = "", // Label for debugging purposes
    )

    // Inner Box representing the filled portion of the progress bar
    Box(
      modifier = Modifier
        .align(Alignment.CenterStart) // Aligns the fill to the start of the container
        .width(
          progressWidth
            .toInt()
            .pxToDp() * animation, // Animate the width based on progress and animation state
        )
        .height(18.dp) // Matches the height of the outer container
        .background(
          color = color, // Sets the color of the filled portion (passed as parameter)
          shape = RoundedCornerShape(64.dp), // Rounded corners to match the outer box
        ),
    ) {
      // Display the label inside the progress bar if there is enough space
      if (isInner) {
        Text(
          modifier = Modifier
            .onSizeChanged { textWidth = it.width } // Capture the width of the label text
            .align(Alignment.CenterEnd) // Align the text to the end of the filled portion
            .padding(end = (threshold * 2).pxToDp()), // Add padding to avoid text-clipping
          text = label, // The label to display, e.g., "150/300"
          fontSize = 12.sp, // Text size
          color = PokedexTheme.colors.absoluteWhite, // White text color for visibility inside the bar
        )
      }
    }

    // Display the label outside the progress bar if there isn't enough space
    if (!isInner) {
      Text(
        modifier = Modifier
          .onSizeChanged { textWidth = it.width } // Capture the width of the label text
          .align(Alignment.CenterStart) // Align the text to the start of the container
          .padding(
            start = progressWidth
              .toInt()
              .pxToDp() + threshold.pxToDp(), // Add padding to avoid overlapping
          ),
        text = label, // The label to display
        fontSize = 12.sp, // Text size
        color = PokedexTheme.colors.absoluteBlack, // Black text color for visibility outside the bar
      )
    }
  }
}

// Preview function for the progress bar with 10% progress
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES) // Support for dark mode
@Composable
private fun PokedexProgressBarPreview1() {
  PokedexTheme {
    Box(
      modifier = Modifier
        .fillMaxWidth() // Makes the box fill the available width
        .height(120.dp) // Sets the height of the box for preview purposes
        .background(PokedexTheme.colors.background), // Background color from theme
    ) {
      PokedexProgressBar(
        modifier = Modifier.align(Alignment.Center), // Center the progress bar in the box
        progress = 0.1f, // 10% progress
        color = PokedexTheme.colors.primary, // Primary color from theme
        label = "150/300", // Label for the progress
      )
    }
  }
}

// Preview function for the progress bar with 50% progress
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES) // Support for dark mode
@Composable
private fun PokedexProgressBarPreview2() {
  PokedexTheme {
    Box(
      modifier = Modifier
        .fillMaxWidth() // Makes the box fill the available width
        .height(120.dp) // Sets the height of the box for preview purposes
        .background(PokedexTheme.colors.background), // Background color from theme
    ) {
      PokedexProgressBar(
        modifier = Modifier
          .fillMaxWidth() // Ensure progress bar spans the full width
          .align(Alignment.Center), // Center the progress bar in the box
        progress = 0.5f, // 50% progress
        color = PokedexTheme.colors.primary, // Primary color from theme
        label = "150/300", // Label for the progress
      )
    }
  }
}
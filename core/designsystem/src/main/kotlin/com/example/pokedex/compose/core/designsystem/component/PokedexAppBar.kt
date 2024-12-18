package com.example.pokedex.compose.core.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pokedex.compose.core.designsystem.theme.PokedexTheme
import com.example.pokedex.compose.designsystem.R

@Composable
fun PokedexAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = PokedexTheme.colors.absoluteWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = PokedexTheme.colors.primary
        )
    )
}

@Preview
@Composable
fun PokedexAppBarPreview() {
    PokedexAppBar()
}
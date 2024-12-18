import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokedex.compose.core.designsystem.theme.PokedexTheme

@Composable
fun BoxScope.PokedexCircularProgressBar() {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = PokedexTheme.colors.primary
    )
}

@Preview
@Composable
fun PokedexCircularProgressBarPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        PokedexCircularProgressBar()
    }
}
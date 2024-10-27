import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
@Preview
fun app() {
    val count = remember { mutableStateOf(0) }
    MaterialTheme {
        Column(
            Modifier.fillMaxSize().padding(top = 15.dp),
            Arrangement.spacedBy(5.dp),
        ) {
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally).width(200.dp),
                painter = BitmapPainter(useResource("image/mht.png", ::loadImageBitmap)),
                contentDescription = "麦皓天"
            )
            AsyncImage(
                load = { loadImageBitmap("https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png") },
                painterFor = { BitmapPainter(it) },
                contentDescription = "Sample",
                modifier = Modifier.align(Alignment.CenterHorizontally).width(200.dp)
            )

        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose Desktop",
        state = rememberWindowState(width = 600.dp, height = 800.dp)
    ) {
        app()
    }
}

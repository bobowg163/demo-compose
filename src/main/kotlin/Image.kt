import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xml.sax.InputSource
import java.io.IOException
import java.net.URL

fun buildPainter(resourcePath: String): Painter {
    val painter: Painter = if (resourcePath.endsWith(".svg")) {
        useResource(resourcePath) {
            loadSvgPainter(it, Density(2f))
        }
    } else if (resourcePath.endsWith(".png") || resourcePath.endsWith(".jpg") ||
        resourcePath.endsWith(".jpeg") || resourcePath.endsWith(".webp") ||
        resourcePath.endsWith(".PNG") || resourcePath.endsWith(".JPG") ||
        resourcePath.endsWith(".JPEG") || resourcePath.endsWith(".WEBP") || resourcePath.endsWith(".ICO")
    ) {
        BitmapPainter(useResource(resourcePath, ::loadImageBitmap))
    } else {
        throw IllegalArgumentException("resource is illegal argument")
    }
    return painter
}

@Composable
fun <T> AsyncImage(
    load: suspend () -> T,
    painterFor: @Composable (T) -> Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val image: T? by produceState<T?>(null) {
        value = withContext(Dispatchers.IO) {
            try {
                load()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
    if (image != null) {
        Image(
            painter = painterFor(image!!),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}

fun loadImageBitmap(url: String): ImageBitmap =
    URL(url).openStream().buffered().use(::loadImageBitmap)

fun loadSvgPainter(url: String, density: Density): Painter =
    URL(url).openStream().buffered().use { loadSvgPainter(it, density) }

fun loadXmlImageVector(url: String, density: Density): ImageVector =
    URL(url).openStream().buffered().use { androidx.compose.ui.res.loadXmlImageVector(InputSource(it), density) }
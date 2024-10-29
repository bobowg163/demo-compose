import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import view.BuildTray
import view.MenuBarWeather

@Composable
@Preview
fun app() {
    val appViewModel = AppViewModel()
    MaterialTheme {
        WeatherPage(appViewModel)
    }
}

fun main() = application {
    val isOpen = rememberSaveable { mutableStateOf(true) }
    val showTray = rememberSaveable { mutableStateOf(true) }
    if (isOpen.value) {
        isOpen.value = BuildTray(isOpen, showTray)
        Window(
            onCloseRequest = { isOpen.value = false },
            title = "三亚天气预报",
            state = rememberWindowState(width = 1080.dp, height = 600.dp),
            icon = painterResource("image/launcher.png"),
        ) {
            showTray.value = MenuBarWeather(isOpen, showTray)
            app()
        }
    }
}
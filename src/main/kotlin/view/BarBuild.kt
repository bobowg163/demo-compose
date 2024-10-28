package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午5:16
 * October28日Monday
 */

@Composable
fun ApplicationScope.BuildTray(isOpen: MutableState<Boolean>, showTray: MutableState<Boolean>): Boolean {
    if (!showTray.value) {
        return isOpen.value
    }
    val trayState = rememberTrayState()
    val infoNotification = rememberNotification("天气预报", "明天的天气很好，建议出门遛弯", Notification.Type.Info)
    val warnNotification =
        rememberNotification("天气预警", "寒冷橙色预警信号，大家要注意保暖!", type = Notification.Type.Warning)
    Tray(
        state = trayState,
        icon = painterResource("image/launcher.png"),
        menu = {
            Item(
                "天气预报",
                onClick = { trayState.sendNotification(infoNotification) }
            )
            Item(
                "天气预警",
                onClick = { trayState.sendNotification(warnNotification) }
            )
            Separator()
            Item("退出", onClick = { isOpen.value = false })
        }
    )
    return isOpen.value
}
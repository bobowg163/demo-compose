package view

import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
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

@Composable
fun FrameWindowScope.MenuBarWeather(isOpen: MutableState<Boolean>, showMenu: MutableState<Boolean>): Boolean {
    var isSubmenuShowing by remember { mutableStateOf(false) }
    var action by remember { mutableStateOf("Last action None") }
    MenuBar {
        Menu("文件", mnemonic = 'F') {
            Item(
                "复制（功能还没有完成）",
                onClick = { action = "Last action None" },
                shortcut = KeyShortcut(Key.C, ctrl = true)
            )
            Item(
                "粘贴（功能还没有完成）",
                onClick = { action = "Last action None" },
                shortcut = KeyShortcut(Key.V, ctrl = true)
            )
            Separator()
            Item("退出", onClick = {isOpen.value = false}, shortcut = KeyShortcut(Key.Escape), mnemonic = 'E')
        }
        Menu("显示", mnemonic = 'A') {
            CheckboxItem("显示托盘", checked = showMenu.value, onCheckedChange = { showMenu.value = !showMenu.value })
            CheckboxItem(
                "高级设置",
                checked = isSubmenuShowing,
                onCheckedChange = { isSubmenuShowing = !isSubmenuShowing })
            if (isSubmenuShowing) {
                Menu("设置") {
                    Item("设置一", onClick = { action = "Last action: Setting 1" })
                    Item("设置二", onClick = { action = "Last action: Setting 2" })
                }
            }
            Separator()
            Item("关于",icon = painterResource("image/launcher.png"),onClick = { action = "关于我们" })

        }
        Menu("帮助", mnemonic = 'H') {
            Item("天气帮助", onClick = { action = "Last action: Help" })
        }
    }
    println("action:$action")
    return showMenu.value
}
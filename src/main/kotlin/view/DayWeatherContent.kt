package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.WeatherModel
import model.weather.DayItemData

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/29 时间 上午8:08
 * October29日Tuesday
 */

@Composable
fun DayWeatherContent(
    weatherModel: WeatherModel?
) {
    val dailyBean = weatherModel?.dailyBean
    val nowBaseBean = weatherModel?.nowBaseBean
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
    ) {
        val modifier = Modifier.weight(1f).padding(5.dp)
        WeatherContentItem(
            modifier = modifier,
            DayItemData(
                "能见度",
                "${nowBaseBean?.vis ?: "0"}公里",
                "当前的能见度",
                "关于能见度",
                "能见度会告诉你可以清晰地看到多远以外的物体，如建筑和山丘等。能见度测量大气透明度，不考虑光照强度或障碍物。能见度10公里及以上为良好。"
            )
        )
        WeatherContentItem(
            modifier = modifier, DayItemData(
                "风",
                "${nowBaseBean?.windDir ?: "南风"}${nowBaseBean?.windScale ?: ""}级",
                "当前风速为${nowBaseBean?.windSpeed ?: "0"}Km/H", "关于风速和阵风", "风速的计算取短时间内的平均值。" +
                        "阵风是高于此平均值的短暂强风。阵风的持续时间通常低于 20 秒。"
            )
        )

    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
private fun WeatherContentItem(modifier: Modifier, data: DayItemData) {
    var showPopupWindow by remember { mutableStateOf(false) }
    Card(modifier = modifier.clickable {
        showPopupWindow = true
    }, shape = RoundedCornerShape(10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = data.title, fontSize = 11.sp)
            Text(
                text = data.value,
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 18.sp,
                color = MaterialTheme.colors.onSecondary
            )
            Text(
                text = data.tip,
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 13.sp,
                color = MaterialTheme.colors.onSecondary
            )
        }

        CursorDropdownMenu(
            showPopupWindow,
            onDismissRequest = { showPopupWindow = false },
            modifier = modifier.width(300.dp).padding(horizontal = 15.dp).padding(bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = data.titleDetails,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSecondary
                )
                IconButton(onClick = { showPopupWindow = false }) {
                    Icon(Icons.Sharp.Close, "Close")
                }
            }
            Text(
                text = data.valueDetails,
                fontSize = 13.sp,
            )
        }
    }
}

@Composable
@Preview
private fun DayWeatherContentPreview() {
    DayWeatherContent(null)
}
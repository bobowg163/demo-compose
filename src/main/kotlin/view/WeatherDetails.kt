package view

import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.weather.WeatherNowBean
import utils.getTimeNameForObs
import utils.getWeatherIcon

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午7:09
 * October28日Monday
 */

@Composable
fun WeatherDetails(
    modifier: Modifier = Modifier,
    nowBean: WeatherNowBean.NowBaseBean?,
    onAddCityClick: () -> Unit,
    onRefreshClick: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onAddCityClick) {
                Icon(Icons.Sharp.Add, contentDescription = null)
            }
            Text(nowBean?.city ?: "三亚", fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))

            Text(nowBean?.obsTime?.getTimeNameForObs() ?: "现在", fontSize = 14.sp)
            IconButton(onClick = onRefreshClick) {
                Icon(Icons.Sharp.Refresh, contentDescription = null)
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RotateWeatherIcon(nowBean?.icon ?: "100")
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text("${nowBean?.temp ?: "0"}°", fontSize = 80.sp, maxLines = 1)
                Text(nowBean?.text ?: "晴", fontSize = 15.sp, maxLines = 1)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherItem(nowBean?.windDir ?: "南风", "${nowBean?.windScale ?: "1"}级")
            WeatherItem("降雨", "${nowBean?.precip ?: "0"}毫米")
            WeatherItem("能见度", "${nowBean?.vis ?: "0"}公里")
            WeatherItem("气压", "${nowBean?.pressure ?: "0"}百帕")

        }
    }


}

@Composable
fun WeatherItem(name: String, value: String) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(name, fontSize = 14.sp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(5.dp))
        Text(value, fontSize = 14.sp, color = Color.DarkGray)
    }
}

/*
* 动画图标
*/
@Composable
fun RotateWeatherIcon(icon: String) {
    val infiniteTransition = rememberInfiniteTransition()
    val modifier = if (icon == "100") {
        val rotate by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(3500, easing = LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
            )
        )
        Modifier.rotate(rotate)
    } else {
        val offsetX by infiniteTransition.animateValue(
            initialValue = (-30).dp,
            targetValue = 30.dp,
            typeConverter = TwoWayConverter(
                { AnimationVector1D(it.value) },
                { it.value.dp }
            ),
            animationSpec = infiniteRepeatable(
                animation = tween(3500, easing = LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
            )
        )
        Modifier.offset(x = offsetX)
    }
    Image(
        painter = painterResource(getWeatherIcon(icon)),
        contentDescription = null,
        modifier = modifier.size(170.dp).padding(10.dp)
    )
}


@Preview
@Composable
private fun WeatherDetailsPreview() {
    WeatherDetails(Modifier, nowBean = null, onAddCityClick = {}, onRefreshClick = {})
}

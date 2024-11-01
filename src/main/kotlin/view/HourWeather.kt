package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.weather.WeatherHourlyBean
import utils.getTimeName
import utils.getWeatherIcon

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午11:00
 * October28日Monday
 */

@Composable
fun HourWeather(hourlyBeanList: List<WeatherHourlyBean.HourlyBean>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "24小时天气预报",
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 7.dp, start = 10.dp, end = 10.dp)
            )
            Divider(modifier = Modifier.padding(horizontal = 10.dp), thickness = 0.4.dp)
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                items(hourlyBeanList) { hourlyBean ->
                    HourWeatherItem(hourlyBean)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun HourWeatherItem(hourlyBean: WeatherHourlyBean.HourlyBean) {
    Column(
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hourlyBean.fxTime?.getTimeName() ?: "现在",
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.height(15.dp))
        Image(
            painter = painterResource(getWeatherIcon(hourlyBean.icon)),
            "",
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "${hourlyBean.temp}°",
            modifier = Modifier.padding(top = 15.dp),
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Preview
@Composable
private fun HourWeatherPreview() {
    val list = arrayListOf<WeatherHourlyBean.HourlyBean>()
    for (index in 1..24) {
        list.add(WeatherHourlyBean.HourlyBean())
    }
    HourWeather(list)
}

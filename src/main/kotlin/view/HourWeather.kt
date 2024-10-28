package view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.weather.WeatherHourlyBean

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午11:00
 * October28日Monday
 */

@Composable
fun HourWeather(
    hourlyBeanList: List<WeatherHourlyBean.HourlyBean>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        
    }
}
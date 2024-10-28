package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.WeatherModel

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午10:12
 * October28日Monday
 */

@Composable
fun rightInfomation(
    modifier: Modifier,
    weatherModel: WeatherModel
) {
    Column (
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //当前空气质量
        AirQuality(weatherModel.airNowBean)
        // 未来24小时天气预报
        HourWeather(weatherModel.hourlyBeanList)
    }
}
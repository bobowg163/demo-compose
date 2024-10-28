import AppViewModel.Companion.DEFAULT_CITY_ID
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.WeatherModel
import view.leftInfomation

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午5:58
 * October28日Monday
 */

@Composable
fun WeatherPage(appViewModel: AppViewModel) {
    val weatherModel by appViewModel.weatherModelData.collectAsState(WeatherModel())
    val currentCityId by appViewModel.currentCityId.collectAsState(DEFAULT_CITY_ID)
    LaunchedEffect(currentCityId) {
        appViewModel.getWeather(currentCityId)
    }
    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        leftInfomation(appViewModel,weatherModel.nowBaseBean,currentCityId)
    }

}
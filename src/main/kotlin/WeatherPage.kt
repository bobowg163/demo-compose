import AppViewModel.Companion.DEFAULT_CITY_ID
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.WeatherModel
import org.jetbrains.skia.Color
import view.leftInformation
import view.rightInfomation

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
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.onPrimary).padding(16.dp)
    ) {
        leftInformation(appViewModel,weatherModel.nowBaseBean,currentCityId)
        val modifier = Modifier.weight(1f).width(500.dp)
        rightInfomation(modifier,weatherModel)
    }

}
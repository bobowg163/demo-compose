import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import model.WeatherModel
import model.city.GeoBean
import model.weather.WeatherDailyBean
import model.weather.WeatherNowBean
import network.PlayWeatherNetwork
import utils.DataStoreUtils
import utils.getDateWeekName

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午3:28
 * October28日Monday
 */
class AppViewModel {
    companion object {
        private const val CURRENT_CITY = "CURRENT_CITY"
        private const val DEFAULT_CITY = "三亚"
        private const val CURRENT_CITY_ID = "CURRENT_CITY_ID"
        const val DEFAULT_CITY_ID = "CN101310218"
    }

    private val playWeatherNetWork = PlayWeatherNetwork

    /*
    *  当前天气
    * */
    private val _weatherModel = MutableStateFlow(WeatherModel())
    val weatherModelData: Flow<WeatherModel>
        get() = _weatherModel

    suspend fun getWeather(locationModel: GeoBean.LocationBean) {
        val location = locationModel.id ?: DEFAULT_CITY_ID
        getWeather(location)
        DataStoreUtils.putData(
            CURRENT_CITY,
            if (location == DEFAULT_CITY_ID) DEFAULT_CITY else locationModel.name
        )
        DataStoreUtils.putData(CURRENT_CITY_ID, location)
    }

    /**
     * 获取天气信息
     *
     * @param location 位置
     */
    suspend fun getWeather(location: String = DEFAULT_CITY_ID) {
        val weatherNow = playWeatherNetWork.getWeatherNow(location)
        val weather24Hour = playWeatherNetWork.getWeather24Hour(location)
        val weather7Day = playWeatherNetWork.getWeather7Day(location)
        val airNow = playWeatherNetWork.getAirNowBean(location)
        val weatherLifeIndicesList = playWeatherNetWork.getWeatherLifeIndicesBean(location)
        buildWeekWeather(weather7Day, weatherNow)
    }

    /*
    * 为了构建7天天气的柱状图
    */
    private fun buildWeekWeather(weather7Day: WeatherDailyBean, weatherNow: WeatherNowBean) {
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        weather7Day.daily.forEach {
            val currentMin = it.tempMin?.toInt() ?: 0
            if (min > currentMin) {
                min = currentMin
            }
            val currentMax = it.tempMax?.toInt() ?: 0
            if (currentMax > max) {
                max = currentMax
            }
        }
        weather7Day.daily.forEachIndexed { index, weatherDailyBean ->
            weatherDailyBean.weekMin = min
            weatherDailyBean.weekMax = max
            if (index == 0) {
                weatherDailyBean.fxDate = "今天"
            } else {
                weatherDailyBean.fxDate = weatherDailyBean.fxDate?.getDateWeekName() ?: "今天"
            }
            if (weatherDailyBean.fxDate == "今天") {
                weatherDailyBean.temp = weatherNow.now.temp?.toInt() ?: -100
            }
        }
    }

}
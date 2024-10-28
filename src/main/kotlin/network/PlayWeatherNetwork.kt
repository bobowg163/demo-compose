package network

import model.Lang
import model.air.AirNowBean
import model.city.GeoBean
import model.city.TopGeoBean
import model.indices.WeatherLifeIndicesBean
import model.weather.WeatherDailyBean
import model.weather.WeatherHourlyBean
import model.weather.WeatherNowBean
import network.service.AirNowService
import network.service.CityLookupService
import network.service.CityWeatherService
import network.service.WeatherLifeIndicesService

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/27 时间 下午8:16
 * 十月27日星期日
 */
object PlayWeatherNetwork {
    private var language: Lang = Lang.ZH_HANS
    private val airNowService = ServiceCreator.create(AirNowService::class.java)

    suspend fun getWeatherLifeIndicesBean(location: String): WeatherLifeIndicesBean =
        airNowService.getWeatherLifeIndicesBean(
            location = location,
            lang = language.code,
            type = "1,2,3,5,6,9"
        )

    private val cityLookupService = ServiceCreator.createCity(CityLookupService::class.java)

    suspend fun getCityLookup(location: String): GeoBean {
        return cityLookupService.getCityLookup(location = location, lang = language.code)
    }

    suspend fun getCityTop(): TopGeoBean {
        return cityLookupService.getCityTop(lang = language.code)
    }

    private val cityWeatherService = ServiceCreator.create(CityWeatherService::class.java)

    suspend fun getWeatherNow(location: String): WeatherNowBean =
        cityWeatherService.getWeatherNow(location = location, lang = language.code)

    suspend fun getWeather24Hour(location: String): WeatherHourlyBean =
        cityWeatherService.getWeather24Hour(location = location, lang = language.code)

    suspend fun getWeather3Day(location: String): WeatherDailyBean =
        cityWeatherService.getWeather3Day(location = location, lang = language.code)

    suspend fun getWeather7Day(location: String): WeatherDailyBean =
        cityWeatherService.getWeather7Day(location = location, lang = language.code)

    private val weatherLifeIndicesService = ServiceCreator.create(WeatherLifeIndicesService::class.java)

    suspend fun getAirNowBean(location: String): AirNowBean =
        weatherLifeIndicesService.getAirNowBean(location = location, lang = language.code)
}
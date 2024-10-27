package service

import retrofit2.http.GET
import retrofit2.http.Query
import javax.swing.text.html.HTML.Attribute.LANG
import javax.xml.stream.Location

/*demo-compose
  service
  bobo
  2024/10/27下午7:32*/
interface CityWeatherService {
    @GET("weather/now")
    suspend fun getWeatherNow(
        @Query("key")
        key:String = WEATHER_KEY,
        @Query("location")
        location: String,
        @Query("lang")
        lang:String = Lang.ZH_HANS.code
    ):WeatherNowBean
}
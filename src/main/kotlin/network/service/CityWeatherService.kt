package service

import WEATHER_KEY
import model.weather.WeatherNowBean
import retrofit2.http.GET
import retrofit2.http.Query


/*demo-compose
  service
  bobo
  2024/10/27下午7:32*/
interface CityWeatherService {
    /**
     * 实时天气
     *
     * @param key 用户认证key
     * @param location 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标
     * @param lang 多语言设置，默认中文
     *
     * 实时温度、体感温度、风力风向、相对湿度、大气压强、降水量、能见度、露点温度、云量等数据。
     */
    @GET("weather/now")
    suspend fun getWeatherNow(
        @Query("key") key: String = WEATHER_KEY,
        @Query("location") location: String,
        @Query("lang") lang: String
    ): WeatherNowBean

}
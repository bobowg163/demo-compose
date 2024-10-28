package network.service

import WEATHER_KEY
import model.weather.WeatherDailyBean
import model.weather.WeatherHourlyBean
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
    /**
     * 未来24小时
     *
     * @param key 用户认证key
     * @param location 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标
     * @param lang 多语言设置，默认中文
     *
     * 温度、天气状况、风力、风速、风向、相对湿度、大气压强、降水概率、露点温度、云量。
     */
    @GET("weather/24h")
    suspend fun getWeather24Hour(
        @Query("key") key: String = WEATHER_KEY,
        @Query("location") location: String,
        @Query("lang") lang: String
    ): WeatherHourlyBean

    /**
     * 未来3天
     *
     * @param key 用户认证key
     * @param location 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标
     * @param lang 多语言设置，默认中文
     *
     * 日出日落、月升月落、最高最低温度、天气白天和夜间状况、风力、风速、风向、相对湿度、大气压强、降水量、降水概率、露点温度、紫外线强度、能见度等。
     */
    @GET("weather/3d")
    suspend fun getWeather3Day(
        @Query("key") key: String = WEATHER_KEY,
        @Query("location") location: String,
        @Query("lang") lang: String
    ):WeatherDailyBean

    /**
     * 未来7天
     *
     * @param key 用户认证key
     * @param location 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标
     * @param lang 多语言设置，默认中文
     *
     * 日出日落、月升月落、最高最低温度、天气白天和夜间状况、风力、风速、风向、相对湿度、大气压强、降水量、降水概率、露点温度、紫外线强度、能见度等。
     */
    @GET("weather/7d")
    suspend fun getWeather7Day(
        @Query("key") key: String = WEATHER_KEY,
        @Query("location") location: String,
        @Query("lang") lang: String
    ):WeatherDailyBean



}
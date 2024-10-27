package network.service

import WEATHER_KEY
import model.indices.WeatherLifeIndicesBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/27 时间 下午9:36
 * 十月27日星期日
 */
interface AirNowService {
    /**
     * 实时空气质量
     *
     * @param key 用户认证key
     * @param location 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标
     * @param lang 多语言设置，默认中文
     *
     * 和风天气生活指数API接口为中国和海外城市提供详细的生活指数实况和预报数据，包括：
    中国天气生活指数：舒适度指数、洗车指数、穿衣指数、感冒指数、运动指数、旅游指数、紫外线指数、空气污染扩散条件指数、
    空调开启指数、过敏指数、太阳镜指数、化妆指数、晾晒指数、交通指数、钓鱼指数、防晒指数
    海外天气生活指数：运动指数、洗车指数、紫外线指数、钓鱼指数
     *
     */
    @GET("indices/1d")
    suspend fun getWeatherLifeIndicesBean(
        @Query("key") key: String = WEATHER_KEY,
        @Query("location") location: String,
        @Query("lang") lang: String,
        @Query("type") type: String
    ): WeatherLifeIndicesBean
}
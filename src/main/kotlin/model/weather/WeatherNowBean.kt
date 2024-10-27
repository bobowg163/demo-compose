package model.weather

import model.Refer

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/27@时间 下午7:49
 * @月 十月
 * @日 27
 * @日 星期日
 * @分钟 49
 */
data class WeatherNowBean(
    val fxLink:String? = null,
    val code:String? = null,
    val  referval : Refer? = null,
    val now:NowBaseBean = NowBaseBean(),
    val updateTime:String? = null
){
    data class NowBaseBean(
        val vis: String? = null,
        var temp: String? = null,
        val obsTime: String? = null,
        val icon: String? = null,
        val wind360: String? = null,
        val windDir: String? = null,
        val pressure: String? = null,
        val feelsLike: String? = null,
        val cloud: String? = null,
        val precip: String? = null,
        val dew: String? = null,
        val humidity: String? = null,
        var text: String? = null,
        val windSpeed: String? = null,
        val windScale: String? = null,
        var city: String? = null

    )
}

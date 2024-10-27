package model.weather

import model.Refer

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/27 时间 下午10:44
 * 十月27日星期日
 */
data class WeatherHourlyBean(
    val fxLink: String? = null,
    val code: String? = null,
    val refer: Refer? = null,
    val updateTime: String? = null,
    val hourly: List<HourlyBean> = ArrayList()
) {

    data class HourlyBean(
        var temp: String? = null,
        var icon: String? = null,
        val wind360: String? = null,
        val windDir: String? = null,
        val pressure: String? = null,
        var fxTime: String? = null,
        val pop: String? = null,
        val cloud: String? = null,
        val precip: String? = null,
        val dew: String? = null,
        val humidity: String? = null,
        val text: String? = null,
        val windSpeed: String? = null,
        val windScale: String? = null
    )
}
package model.air

import model.Refer

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午3:20
 * October28日Monday
 */
data class AirNowBean(
    val fxLink: String? = null,
    val code: String? = null,
    val refer: Refer? = null,
    val now: NowBean = NowBean(),
    val station: List<StationItem>? = null,
    val updateTime: String? = null,
) {

    data class NowBean(
        val no2: String? = null,
        val o3: String? = null,
        val level: String? = null,
        val pm2p5: String? = null,
        val pubTime: String? = null,
        val so2: String? = null,
        val aqi: String? = null,
        val pm10: String? = null,
        val category: String? = null,
        val co: String? = null,
        var primary: String? = null,
    )
}
package model

import model.air.AirNowBean
import model.indices.WeatherLifeIndicesBean
import model.weather.WeatherDailyBean
import model.weather.WeatherHourlyBean
import model.weather.WeatherNowBean

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午3:35
 * October28日Monday
 */
data class WeatherModel(
    val nowBaseBean: WeatherNowBean.NowBaseBean? = WeatherNowBean.NowBaseBean(),
    val hourlyBeanList: List<WeatherHourlyBean.HourlyBean> = arrayListOf(),
    val dailyBean: WeatherDailyBean.DailyBean? = WeatherDailyBean.DailyBean(),
    val dailyBeanList: List<WeatherDailyBean.DailyBean> = arrayListOf(),
    val airNowBean: AirNowBean.NowBean? = AirNowBean.NowBean(),
    val weatherLifeList: List<WeatherLifeIndicesBean.WeatherLifeIndicesItem> = arrayListOf(),
    val fxLink: String? = null
)

fun WeatherModel.isEmpty(): Boolean {
    return nowBaseBean == null || hourlyBeanList.isEmpty() || dailyBean == null || dailyBeanList.isEmpty() || airNowBean == null || weatherLifeList.isEmpty() || fxLink == null
}

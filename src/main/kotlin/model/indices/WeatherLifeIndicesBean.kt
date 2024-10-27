package model.indices

import model.Refer
import utils.lifePrefix

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/27 时间 下午9:38
 * 十月27日星期日
 */
data class WeatherLifeIndicesBean(
    val fxLink: String?=null,
    val code:String?=null,
    val refer: Refer?=null,
    val daily:List<WeatherLifeIndicesItem>? = null,
    val updateTime:String?=null,
){
    data class WeatherLifeIndicesItem(
        val date: String? = null,
        val level: String? = null,
        val name: String? = null,
        val text: String? = null,
        val type: String? = null,
        val category: String? = null,
        var imgRes: String = "${lifePrefix}ic_life_sport.svg",
    )
}

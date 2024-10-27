package model.city

import model.Refer


/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/27 时间 下午9:59
 * 十月27日星期日
 */
data class TopGeoBean(
    val code: String? = null,
    val refer: Refer? = null,
    val topCityList: List<GeoBean.LocationBean>? = null
)

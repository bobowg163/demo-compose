package model.weather

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/27 时间 下午10:59
 * 十月27日星期日
 */

/**
 * 当天天气详情数据
 */
data class DayItemData(
    var title: String,
    var value: String,
    var tip: String,
    var titleDetails: String,
    var valueDetails: String
)
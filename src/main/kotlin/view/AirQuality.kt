package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.air.AirNowBean

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午10:26
 * October28日Monday
 */

@Composable
fun AirQuality(airNowBean: AirNowBean.NowBean?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "空气质量", fontSize = 13.sp, modifier = Modifier
                    .padding(bottom = 7.dp)
            )
            Divider(thickness = 0.4.dp)
            Text(
                text = "${airNowBean?.aqi ?: "10"} - ${
                    airNowBean?.category ?: "优"
                }",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 20.sp,
                color = MaterialTheme.colors.onSecondary
            )
            Text(
                text = "当前AQI（CN）为：${airNowBean?.aqi ?: "10"}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            AirQualityProgress((airNowBean?.aqi ?: "10").toInt())
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

/**
 * 空气质量图
 * 0-50	一级	优	绿色
 * 51-100	二级	良	黄色
 * 101-150	三级	轻度污染	橙色
 * 151-200	四级	中度污染	红色
 * 201-300	五级	重度污染	紫色
 * >300	六级	严重污染	褐红色
 *
 * @param aqi 空气质量数值
 */
@Composable
private fun AirQualityProgress(aqi: Int) {
    val aqiValue = if (aqi < 500) {
        aqi
    } else {
        500
    }
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        drawLine(
            brush = Brush.linearGradient(
                0.0f to Color.Gray,
                0.1f to Color.Red,
                0.2f to Color.Blue,
                0.3f to Color.Black,
                0.4f to Color.Cyan,
                1.0f to Color.LightGray,
            ),
            start = Offset.Zero,
            end = Offset(size.width, 0f),
            strokeWidth = 20f,
            cap = StrokeCap.Round,
        )
        drawPoints(
            points = arrayListOf(
                Offset(size.width / 500 * aqiValue, 0f)
            ),
            pointMode = PointMode.Points,
            color = Color.White,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
        )
    }
}

@Preview
@Composable
private fun AirQualityPreview() {
    AirQuality(null)
}
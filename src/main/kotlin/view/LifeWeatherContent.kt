package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.indices.WeatherLifeIndicesBean
import utils.lifePrefix

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/29 时间 上午11:44
 * October29日Tuesday
 */

@Composable
fun LifeWeatherContent(weatherLifeList: List<WeatherLifeIndicesBean.WeatherLifeIndicesItem>?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "生活指数",
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 7.dp, start = 10.dp, end = 10.dp)
            )
            Divider(modifier = Modifier.padding(horizontal = 10.dp), thickness = 0.4.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 5.dp, end = 5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                val modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                WeatherLifeItem(modifier, weatherLifeList.getWeatherLifeItem(0))
                WeatherLifeItem(modifier, weatherLifeList.getWeatherLifeItem(1))
                WeatherLifeItem(modifier, weatherLifeList.getWeatherLifeItem(2))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 5.dp, end = 5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                val modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                WeatherLifeItem(modifier, weatherLifeList.getWeatherLifeItem(3))
                WeatherLifeItem(modifier, weatherLifeList.getWeatherLifeItem(4))
                WeatherLifeItem(modifier, weatherLifeList.getWeatherLifeItem(5))
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
private fun List<WeatherLifeIndicesBean.WeatherLifeIndicesItem>?.getWeatherLifeItem(index: Int) =
    if (this?.isEmpty() == true) null else this?.get(index)

@Composable
fun WeatherLifeItem(modifier: Modifier, item: WeatherLifeIndicesBean.WeatherLifeIndicesItem?) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            painter = painterResource(item?.imgRes ?: "${lifePrefix}ic_life_sport.svg"),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = item?.name ?: "运动指数", fontSize = 12.sp)
            Text(
                text = item?.category ?: "较适宜",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSecondary
            )
        }

    }
}

@Preview
@Composable
private fun LifeWeatherContentPreview() {
    LifeWeatherContent(null)
}
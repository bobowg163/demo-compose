package view

import AppViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utils.ShowDialog

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午8:04
 * October28日Monday
 */

@Composable
fun SearchCity(modifier: Modifier = Modifier, appViewModel: AppViewModel, backClick: () -> Unit) {
    var inputText by rememberSaveable { mutableStateOf("三亚") }
    val showDialog = rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(inputText) {
        if (inputText.isBlank()) {
            appViewModel.searchCity(inputText)
        } else {
            appViewModel.searchCity()
        }
    }
    val scope = rememberCoroutineScope()
    val locationListData by appViewModel.locationListData.collectAsState(arrayListOf())
    val toplocationListData by appViewModel.topLocationListData.collectAsState(arrayListOf())
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = backClick) {
                Icon(Icons.Sharp.ArrowBack, contentDescription = "返回")
            }
            Box(
                modifier = Modifier.weight(1f).padding(end = 10.dp).height(30.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = inputText,
                    onValueChange = {
                        inputText = it
                    },
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    textStyle = TextStyle(fontSize = 14.sp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {

                    }),
                    maxLines = 1,
                )
            }
            Text(
                if (inputText.isNotBlank()) "取消" else "编辑",
                fontSize = 15.sp,
                modifier = Modifier.clickable {
                    if (inputText.isNotBlank()) {
                        inputText = ""
                        appViewModel.clearSearchCity()
                    } else {
                        showDialog.value = true
                    }
                })
            ShowDialog(showDialog, "建议", "多学一个知识点，就少说一句求人的话！", "努力", "共勉") {}
        }
    }
}
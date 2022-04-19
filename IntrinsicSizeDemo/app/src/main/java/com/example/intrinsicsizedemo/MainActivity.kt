package com.example.intrinsicsizedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.example.intrinsicsizedemo.ui.theme.IntrinsicSizeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntrinsicSizeDemoTheme {
                // 테마의 'background' 색상을 이용하는 서피스 컨테이너
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    var textState by remember { mutableStateOf("") }

    val onTextChange = { text: String ->
        textState = text
    }

    Column(Modifier.width(200.dp).padding(5.dp)) {
        Column(Modifier.width(IntrinsicSize.Min)) {
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = textState
            )
            Box(Modifier.height(10.dp).fillMaxWidth().background(Color.Blue))
        }
        MyTextField(text = textState, onTextChange = onTextChange)

    }

}

@Composable
fun MyTextField(text: String, onTextChange : (String) -> Unit) {

    TextField(
        value = text,
        onValueChange = onTextChange
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IntrinsicSizeDemoTheme {
        MainScreen()
    }
}
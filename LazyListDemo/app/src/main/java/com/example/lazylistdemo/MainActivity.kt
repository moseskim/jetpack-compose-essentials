package com.example.lazylistdemo

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
import com.example.lazylistdemo.ui.theme.LazyListDemoTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.*

import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {

    private var itemArray: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        itemArray = resources.getStringArray(R.array.car_array)

        super.onCreate(savedInstanceState)
        setContent {
            LazyListDemoTheme {
                // 테마의 'background' 색상을 이용하는 서피스 컨테이너
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(itemArray = itemArray as Array<out String>)
                }
            }
        }
    }
}

@Composable
fun MainScreen(itemArray: Array<out String>) {

    val context = LocalContext.current

    val onListItemClick = { text : String ->

        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    LazyColumn {
        items(itemArray) { model ->
            MyListItem(item = model, onItemClick = onListItemClick)
        }
    }
}

@Composable
fun MyListItem(item: String, onItemClick: (String) -> Unit) {
    Card(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(item) },
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageLoader(item)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ImageLoader(item: String) {

    val url = "https://www.ebookfrenzy.com/book_examples/car_logos/" + item.substringBefore(" ") + "_logo.png"

    Image(
        painter = rememberImagePainter(url),
        contentDescription = "car image",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(75.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val itemArray: Array<String> = arrayOf("Cadillac Eldorado",
        "Ford Fairlane", "Plymouth Fury")

    LazyListDemoTheme {
        MainScreen(itemArray = itemArray)
    }
}
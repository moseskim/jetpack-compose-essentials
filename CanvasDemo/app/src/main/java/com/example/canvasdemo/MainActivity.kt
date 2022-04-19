package com.example.canvasdemo

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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.imageResource

import java.lang.Math.PI
import java.lang.Math.sin

import com.example.canvasdemo.ui.theme.CanvasDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasDemoTheme {
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
    DrawImage()
}

@Composable
fun DrawImage() {

    val image = ImageBitmap.imageResource(id = R.drawable.vacation)

    Canvas(
        modifier = Modifier
            .size(360.dp, 270.dp)
    ) {
        drawImage(
            image = image,
            topLeft = Offset(x = 0f, y = 0f),
            colorFilter = ColorFilter.tint(
                color = Color(0xADFFAA2E),
                blendMode = BlendMode.ColorBurn
            )
        )
    }
}

@Composable
fun DrawPoints() {
    Canvas(modifier = Modifier.size(300.dp)) {

        val height = size.height
        val width = size.width

        val points = mutableListOf<Offset>()

        for (x in 0..size.width.toInt()) {
            val y = (sin(x * (2f * PI /   width))
                    * (height / 2) + (height / 2)).toFloat()
            points.add(Offset(x.toFloat(), y))
        }
        drawPoints(
            points = points,
            strokeWidth = 3f,
            pointMode = PointMode.Points,
            color = Color.Blue
        )
    }
}

@Composable
fun DrawPath() {
    Canvas(modifier = Modifier.size(300.dp)) {

        val path = Path().apply {
            moveTo(0f, 0f)
            quadraticBezierTo(50.dp.toPx(), 200.dp.toPx(),
                300.dp.toPx(), 300.dp.toPx())
            lineTo(270.dp.toPx(), 100.dp.toPx())
            quadraticBezierTo(60.dp.toPx(), 80.dp.toPx(), 0f, 0f)
            close()
        }

        drawPath(
            path = path,
            Color.Blue,
        )
    }
}

@Composable
fun DrawArc() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawArc(
            Color.Blue,
            startAngle = 20f,
            sweepAngle = 90f,
            useCenter = true,
            size = Size(250.dp.toPx(), 250.dp.toPx())
        )
    }
}

@Composable
fun ShadowCircle() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val radius = 150.dp.toPx()
        val colorList: List<Color> =
            listOf(Color.Blue, Color.Black)

        val brush = Brush.horizontalGradient(
            colors = colorList,
            startX = 0f,
            endX = 300.dp.toPx(),
            tileMode = TileMode.Repeated
        )

        drawCircle(
            brush = brush,
            radius = radius
        )
    }
}

@Composable
fun RadialFill() {
    Canvas(modifier = Modifier.size(300.dp)) {

        val canvasWidth = size.width
        val canvasHeight = size.height
        val radius = 150.dp.toPx()
        val colorList: List<Color> = listOf(Color.Red, Color.Blue,
            Color.Magenta, Color.Yellow, Color.Green, Color.Cyan)

        val brush = Brush.radialGradient(
            colors = colorList,
            center = center,
            radius = radius,
            tileMode = TileMode.Repeated
        )

        drawCircle(
            brush = brush,
            center = center,
            radius = radius
        )
    }
}

@Composable
fun GradientFill() {

    Canvas(modifier = Modifier.size(300.dp)) {
        val canvasSize = size
        val colorList: List<Color> = listOf(Color.Red, Color.Blue,
            Color.Magenta, Color.Yellow, Color.Green, Color.Cyan)

        val brush = Brush.horizontalGradient(
            colors = colorList,
            startX = 0f,
            endX = 300.dp.toPx(),
            tileMode = TileMode.Repeated
        )

        drawRect(
            brush = brush,
            size = canvasSize
        )
    }
}

@Composable
fun DrawOval() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawOval(
            color = Color.Blue,
            topLeft = Offset(x = 25.dp.toPx(), y = 90.dp.toPx()),
            size = Size(
                width = canvasHeight - 50.dp.toPx(),
                height = canvasHeight / 2 - 50.dp.toPx()
            ),
            style = Stroke(width = 12.dp.toPx())
        )
    }
}

@Composable
fun DrawCircle() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawCircle(
            color = Color.Blue,
            center = center,
            radius = 120.dp.toPx()
        )
    }
}

@Composable
fun DrawRect() {
    Canvas(modifier = Modifier.size(300.dp)) {
        rotate(45f) {
            drawRect(
                color = Color.Blue,
                topLeft = Offset(200f, 200f),
                size = size / 2f
            )
        }
    }
}

@Composable
fun DrawLine() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val height = size.height
        val width = size.width

        drawLine(
            start = Offset(x= 0f, y = 0f),
            end = Offset(x = width, y = height),
            color = Color.Blue,
            strokeWidth = 16.0f,
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(30f, 10f, 10f, 10f), phase = 0f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CanvasDemoTheme {
        MainScreen()
    }
}
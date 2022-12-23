package com.example.moneykeeper.pieChart


import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneykeeper.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.math.PI
import kotlin.math.atan2

data class PieChartInput(
    val color: Color,
    val value: Int,
    val description: String,//TODO rename to expense
    val isTapped: Boolean = false
)

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    radius: Float = 500f,
    innerRadius: Float = 250f,
    transparentWidth: Float = 70f,
    input: List<PieChartInput>,
    centerText: String = ""
) {
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var inputList by remember {
        mutableStateOf(input)
    }
    var isCenterTapped by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    )
    {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(true) {
                detectTapGestures(
                    onTap = {offset ->
                        val tapAngleInDegrees = (atan2(
                                x = circleCenter.y - offset.y,
                                y = circleCenter.x - offset.x
                        ) * (180 / PI).toFloat() -90f).mod(360f)
                        val centerClicked = if(tapAngleInDegrees<90) {
                            offset.x<circleCenter.x+innerRadius && offset.y<circleCenter.y+innerRadius
                        }else if(tapAngleInDegrees<180){
                            offset.x>circleCenter.x-innerRadius && offset.y<circleCenter.y+innerRadius
                        }else if(tapAngleInDegrees<270){
                            offset.x>circleCenter.x-innerRadius && offset.y>circleCenter.y-innerRadius
                        }else{
                            offset.x<circleCenter.x+innerRadius && offset.y>circleCenter.y-innerRadius
                        }

                        if (centerClicked){
                            inputList = inputList.map {
                                it.copy(isTapped = !isCenterTapped)
                            }
                            isCenterTapped = !isCenterTapped
                        } else {
                            val anglePerValue = 360f/input.sumOf {
                                it.value
                            }
                            var currAngle = 0f
                            inputList.forEach { pieChartInput ->

                                currAngle += pieChartInput.value * anglePerValue
                            }
                        }
                    }
                )
            }){
                val width = size.width
            val height = size.height
            circleCenter = Offset(x = width/2f, y = height/2f)

            val totalValue = input.sumOf {
                it.value
            }
            val anglePerValue = 360f/totalValue
            var currentStartAngle = 0f

            inputList.forEach{ pieChartInput ->
                val scale = if(pieChartInput.isTapped) 1.1f else 1.0f
                val angleToDraw = pieChartInput.value * anglePerValue
                scale(scale) {
                    drawArc(
                        color = pieChartInput.color,
                        startAngle = currentStartAngle,
                        sweepAngle = angleToDraw,
                        useCenter = true,
                        size = Size(
                            width = radius * 2f,
                            height = radius * 2f,
                        ),
                        topLeft = Offset(
                            (width - radius * 2f) / 2f,
                            (height - radius * 2f) / 2f
                        )
                    )
                    currentStartAngle += angleToDraw
                }
                var rotateAngle = currentStartAngle-anglePerValue/2f-90f
                var factor = 1f
                if (rotateAngle>90f){
                    rotateAngle = (rotateAngle+180).mod(360f)
                    factor = -0.92f
                }
                val percentage = (pieChartInput.value/totalValue.toFloat()*100).toInt()

                drawContext.canvas.nativeCanvas.apply {//TODO make it appear when isTapped = true
                    if (percentage>3){
                        rotate(rotateAngle){
                            drawText(
                                "$percentage",
                                circleCenter.x,
                                circleCenter.y + (radius-(radius-innerRadius)/2f)*factor,
                                Paint().apply {
                                    textSize = 13.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = white.toArgb()
                                }
                            )
                        }
                    }
                }
                if(pieChartInput.isTapped){
                    val tabRotation = currentStartAngle - anglePerValue - 90f
                    rotate(tabRotation){
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f, radius*1.2f),
                            color = gray,
                            cornerRadius = CornerRadius(15f,15f)
                        )
                    }
                    rotate(tabRotation*angleToDraw){
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f, radius*1.2f),
                            color = gray,
                            cornerRadius = CornerRadius(15f,15f)
                        )
                    }
                    rotate(rotateAngle){
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                "${pieChartInput.description}: ${pieChartInput.value}",
                                circleCenter.x,
                                circleCenter.y + radius*1.3f*factor,
                                Paint().apply {
                                    textSize = 22.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = white.toArgb()
                                    isFakeBoldText = true
                                }
                            )
                        }
                    }
                }
            }
            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    circleCenter.x,
                    circleCenter.y,
                    innerRadius,
                    Paint().apply {
                        color = white.copy(alpha = 0.6f).toArgb()
                        setShadowLayer(10f, 0f, 0f, gray.toArgb())

                    }
                )
            }
            drawCircle(
                color = white,
                radius = innerRadius+transparentWidth/2f
            )
        }
        Text(
            centerText,
            modifier = Modifier
                .width(Dp(innerRadius/1.5f))
                .padding(25.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            textAlign = TextAlign.Center
        )
    }
}

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun PieChartYTTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
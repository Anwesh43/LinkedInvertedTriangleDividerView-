package com.example.invertedtriangledividerview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val parts : Int = 3
val lines : Int = 5
val scGap : Float = 0.02f / parts
val strokeFactor : Float = 90f
val sizeFactor : Float = 4.9f
val colors : Array<Int> = arrayOf(
    "#F44336",
    "#009688",
    "#3F51B5",
    "#FF5722",
    "#4CAF50"
).map {
    Color.parseColor(it)
}.toTypedArray()
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val deg : Float = 45f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawInvertedTriangleDivider(scale : Float, w : Float, h : Float, paint : Paint) {
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val size : Float = Math.min(w, h) / sizeFactor
    val gap : Float = size * Math.cos(Math.PI  / 4).toFloat()
    save()
    translate(w / 2, h / 2)
    for (j in 0..1) {
        save()
        rotate(deg * sf2 * (1 - 2 * j))
        drawLine(0f, 0f, 0f, -size * sf1, paint)
        for (k in 0..(lines - 1)) {
            val sf3k : Float = sf3.divideScale(k, lines)
            save()
            translate(0f, (-size + k * gap))
            drawLine(-gap * sf3k, 0f, gap * sf3k, 0f, paint)
            restore()
        }
        restore()
    }
    restore()
}

fun Canvas.drawITDNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawInvertedTriangleDivider(scale, w, h, paint)
}

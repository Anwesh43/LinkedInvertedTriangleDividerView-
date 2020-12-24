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

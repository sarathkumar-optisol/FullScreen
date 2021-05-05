package com.droid.fullscreen

import android.app.Activity
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlin.math.abs

class MainActivity : AppCompatActivity() , GestureDetector.OnGestureListener{

    lateinit var gestureDetector: GestureDetector

    var x1 = 0.0f
    var x2 = 0.0f
    var y1 = 0.0f
    var y2 = 0.0f

    companion object{
        const val MIN_DISTANCE = 150
    }

    var isFullScreen = true
    var isSticky = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gestureDetector = GestureDetector(this,this)


        val button = findViewById<Button>(R.id.btn)

//        button.setOnClickListener {
//            if(isFullScreen){
//                showSystemUI()
//                isFullScreen = false
//            }else{
//                hideSystemUI()
//                isFullScreen = true
//            }
//        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {


        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        gestureDetector.onTouchEvent(event)

        when(event?.action){
            0->{
                 x1 = event.x
                 y1 = event.y

                hideSystemUI()
                Toast.makeText(this,"Something Happened",Toast.LENGTH_SHORT).show()
            }
            1 ->{
                x2 = event.x
                y2 = event.y

                val valuex= x2-x1
                val valuey = y2-y1

                if (abs(valuex) > MIN_DISTANCE){
                    if (x2 > x1){
                        hideSystemUI()
                        Toast.makeText(this,"Right",Toast.LENGTH_SHORT).show()
                    }else{
                        hideSystemUI()
                        Toast.makeText(this,"Left",Toast.LENGTH_SHORT).show()

                    }
                }
                else if (abs(valuey) > MIN_DISTANCE){
                    if (y2 > y1){
                        hideSystemUI()
                    }else{
                        hideSystemUI()
                    }
                }
            }
        }


        return super.onTouchEvent(event)

    }
    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }




}
package com.angcyo.drawable

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.root_layout).background = CheckerboardDrawable.create()

//        findViewById<TextView>(R.id.text1).apply {
//            setCompoundDrawablesRelative(GradientDrawable().apply {
//                shape = GradientDrawable.LINE
//                setStroke(2, Color.RED)
////            setSize(20, 10)
//                setBounds(10, 0, 40, 20)
//            }, null, null, null)
//
//            background = GradientDrawable().apply {
//                shape = GradientDrawable.LINE
//                setStroke(10, Color.RED)
////                setSize(20, 40)
//                setBounds(0, 20, 20, 20)
//                gravity = Gravity.BOTTOM
//            }
//        }
//
//        findViewById<View>(R.id.view1).background = GradientDrawable().apply {
//            shape = GradientDrawable.LINE
//            setStroke(10, Color.RED)
//            setSize(20, 40)
//            setBounds(10, 10, 40, 20)
//        }
//
//        findViewById<View>(R.id.view3).background = LayerDrawable(emptyArray()).apply {
//            val layer = addLayer(GradientDrawable().apply {
//                shape = GradientDrawable.RECTANGLE
//                setStroke(10, Color.YELLOW)
//                cornerRadius = 10f
//                setColor(Color.BLACK)
//            })
//            setLayerInset(layer, -10, -10, 0, 0)
//        }

        findViewById<View>(R.id.view1).background = resources.getDrawable(R.drawable.shape1)

        findViewById<View>(R.id.view2_1).background = RDrawable.get(this)
            .strokeWidth(10)
            .strokeColor(Color.YELLOW)
            .solidColor(Color.RED)
            .cornerRadiiLeft(10f)
            .doIt()

        findViewById<View>(R.id.view2_2).background = RDrawable.get(this)
            .strokeWidth(10)
            .strokeColor(Color.YELLOW)
            .cornerRadiiRight(20f)
            .doIt()

        findViewById<View>(R.id.view2_3).background = RDrawable.get(this)
            .strokeColor(Color.YELLOW)
            .solidColor(Color.BLUE)
            .cornerRadius(20f)
            .doIt()

        findViewById<View>(R.id.view2_4).background = RDrawable.get(this)
            .solidColor(Color.RED)
            .cornerRadius(20f)
            .pressed(true)
            .andSelected(true)
            .andChecked(true)
            .reset()
            .stroke(4, Color.BLACK)
            .normal()
            .state()

        findViewById<View>(R.id.view2_5).background = RDrawable.get(this)
            .solidColor(Color.MAGENTA)
            .cornerRadius(20f)
            .andRippleContent()
            .rippleColor(Color.WHITE)
            .ripple()

        findViewById<View>(R.id.view3_1).background = RDrawable.get(this)
            .rippleColor(Color.YELLOW)
            .ripple()

        findViewById<View>(R.id.view3_2).background = RDrawable.get(this)
            .rippleColor(Color.YELLOW)
            .solidColor(Color.BLUE)
            .cornerRadius(120f)
            .pressed(true)
            //.andRippleContent()
            .andRippleMask()
            .ripple()
    }

    override fun onPostResume() {
        super.onPostResume()
        val drawable1 = resources.getDrawable(R.drawable.shape1)
        val drawable2 = resources.getDrawable(R.drawable.shape2)
        val drawable3 = resources.getDrawable(R.drawable.shape_line)

        findViewById<View>(R.id.view2).background = drawable3.apply {
            //            setBounds(0, 0, 100, 100)
        }
        drawable1
    }
}

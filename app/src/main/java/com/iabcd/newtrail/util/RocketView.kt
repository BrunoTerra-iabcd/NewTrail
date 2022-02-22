package com.iabcd.newtrail.util

import android.animation.Animator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.iabcd.newtrail.R

class RocketView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var canLaunch = true

    fun animateToCoordinates(x: Float, y: Float,adapterPosition : Int,onFinish : () -> Unit) {

        if (!canLaunch) return

        this.animate().apply {
            this.x(x)
            this.y(y)

            if (adapterPosition % 2 == 0) {
                this.rotation(20f)
            } else {
                this.rotation(-20f)
            }
            this.duration = 1000


        }.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                canLaunch = false
                setExploringPlanet(false)
            }

            override fun onAnimationEnd(p0: Animator?) {
                canLaunch = true
                setExploringPlanet(true)
                onFinish()
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }

        })
    }

    private fun setExploringPlanet(isExploring : Boolean){
        if (isExploring){
            this.setImageResource(R.drawable.ic_foguete_no_planeta)
        }else{
            this.setImageResource(R.drawable.ic_foguete)
        }
    }

}
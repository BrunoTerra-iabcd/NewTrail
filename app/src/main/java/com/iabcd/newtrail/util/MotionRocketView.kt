package com.iabcd.newtrail.util

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import com.iabcd.newtrail.R
import com.iabcd.newtrail.model.Holder

class MotionRocketView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        const val PLANET_SCALE_BY = .5f
        const val PLANET_SCALE_VALUE = 1.5f
    }

    private var canExplore = true
    private var currentMotionLayout: MotionLayout? = null
    private var currentHolder: Holder? = null
    private var currentAdapterPosition: Int = -1

    fun animateToCoordinates(
        planetView: View,
        motionLayout: MotionLayout,
        adapterPosition: Int,
        holder: Holder,
        onFinish: () -> Unit
    ) {

        if (!canExplore) return

        attachToPlanet(planetView, holder, adapterPosition, motionLayout)

        this.animate().apply {

            val pointers = IntArray(2)
            planetView.getLocationOnScreen(pointers)

            this.x(pointers[0].toFloat())
            this.y(pointers[1].toFloat())

            if (currentAdapterPosition % 2 == 0) {
                this.rotation(20f)
            } else {
                this.rotation(-20f)
            }
            this.duration = 1000
            attachAnimationListener(this) {
                onFinish()
            }
        }
    }

    fun getAttachedHolder() = currentHolder

    private fun attachAnimationListener(
        animator: ViewPropertyAnimator,
        onFinish: () -> Unit
    ) {
        animator.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                canExplore = false
                setExploringPlanet(false)
            }

            override fun onAnimationEnd(p0: Animator?) {
                canExplore = true
                setExploringPlanet(true)
                scalePlanet()
                onFinish()
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }

        })
    }

    private fun attachToPlanet(
        planetView: View,
        holder: Holder,
        position: Int,
        motionLayout: MotionLayout
    ) {

        this.currentHolder = holder
        this.currentAdapterPosition = position

        unscalePlanet()
        this.currentMotionLayout = motionLayout
    }

    private fun scalePlanet() {
        currentMotionLayout?.transitionToEnd()
     }

     private fun unscalePlanet() {
         currentMotionLayout?.transitionToStart()
     }

    private fun setExploringPlanet(isExploring: Boolean) {
        if (isExploring) {
            this.setImageResource(R.drawable.ic_foguete_no_planeta)
        } else {
            this.setImageResource(R.drawable.ic_foguete)
        }
    }

}
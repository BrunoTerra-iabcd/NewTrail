package com.iabcd.newtrail.util.motion

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.appcompat.widget.AppCompatImageView
import com.iabcd.newtrail.R

class MotionRocketView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var canExplore = true
    private var currentAdapterPosition: Int = -1

    fun animateToCoordinates(
        planetView: View,
        adapterPosition: Int,
        onFinish: () -> Unit
    ) {

        if (!canExplore) return

        this.currentAdapterPosition = adapterPosition

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

    fun getCurrentAttachedPosition() = currentAdapterPosition

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
                onFinish()
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }

        })
    }

    private fun setExploringPlanet(isExploring: Boolean) {
        if (isExploring) {
            this.setImageResource(R.drawable.ic_foguete_no_planeta)
        } else {
            this.setImageResource(R.drawable.ic_foguete)
        }
    }
}
package com.iabcd.newtrail.util.motion


import androidx.constraintlayout.motion.widget.MotionLayout
import com.iabcd.newtrail.R

class MotionHandler()  {

    private var currentMotionLayout: MotionLayout? = null

    fun scalePlanet(motionTarget: MotionLayout,onTransitionToEnd: () -> Unit = {}) {

        unscalePlanet()

        this.currentMotionLayout = motionTarget
        currentMotionLayout?.transitionToEnd()
        attachEndListener(onTransitionToEnd)
    }

    private fun unscalePlanet(onTransitionToStart: () -> Unit = {}) {
        currentMotionLayout?.transitionToStart()
    }

    private fun attachEndListener(function : () -> Unit){
        currentMotionLayout?.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                function.invoke()
                currentMotionLayout?.removeTransitionListener(this)
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }

        })
    }

}
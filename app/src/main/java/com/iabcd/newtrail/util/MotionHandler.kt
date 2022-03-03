package com.iabcd.newtrail.util

import androidx.constraintlayout.motion.widget.MotionLayout

class MotionHandler {

    private var currentMotionLayout: MotionLayout? = null

     fun scalePlanet(motionTarget : MotionLayout) {

        unscalePlanet()

        this.currentMotionLayout = motionTarget
        currentMotionLayout?.transitionToEnd()
    }

     private fun unscalePlanet() {
        currentMotionLayout?.transitionToStart()
    }

}
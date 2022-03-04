package com.iabcd.newtrail.util.motion

import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.iabcd.newtrail.model.Holder

data class MotionClickResponse(
    val clickedView: View,
    val holder: Holder,
    val adapterPosition: Int,
    val currentMotionLayout: MotionLayout,
    val operationType : Int
) {
}
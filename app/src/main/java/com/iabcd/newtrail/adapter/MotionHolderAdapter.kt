package com.iabcd.newtrail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.iabcd.newtrail.R
import com.iabcd.newtrail.databinding.RowHolderMotionLeftBinding
import com.iabcd.newtrail.databinding.RowHolderMotionRightBinding
import com.iabcd.newtrail.model.Holder
import com.iabcd.newtrail.util.motion.MotionClickResponse
import com.iabcd.newtrail.util.motion.MotionRocketView

class MotionHolderAdapter(
    private val items: List<Holder>,
    private val rocketView: MotionRocketView,
    private val onClick: (MotionClickResponse) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_LEFT = 0
        private const val TYPE_RIGHT = 1

        const val OP_PLANET = 0
        const val OP_NEXT = 1
        const val OP_REPEAT = 2
    }

    inner class MotionViewHolder(private val mBinder: ViewBinding) :
        RecyclerView.ViewHolder(mBinder.root) {

        fun bind(holder: Holder, position: Int) {

            mBinder.root.findViewById<TextView>(R.id.row_golder_motion_txtName).text = holder.name
            mBinder.root.findViewById<ImageView>(R.id.row_golder_motion_imagePlanet)
                .setOnClickListener {
                    onClick(
                        MotionClickResponse(
                            it, holder,
                            position, mBinder.root as MotionLayout, OP_PLANET
                        )
                    )
                }
            mBinder.root.findViewById<Button>(R.id.button10).setOnClickListener {
                onClick(
                    MotionClickResponse(
                        it,
                        holder,
                        position,
                        mBinder.root as MotionLayout,
                        OP_NEXT
                    )
                )
            }

            handlePlanetAnimationRecycle(position)
        }

        private fun handlePlanetAnimationRecycle(position: Int) {

            when (position) {

                rocketView.getCurrentAttachedPosition() -> (mBinder.root as MotionLayout).transitionToEnd()
                else -> (mBinder.root as MotionLayout).transitionToStart()

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return when (viewType) {

            TYPE_LEFT -> MotionViewHolder(
                RowHolderMotionLeftBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> MotionViewHolder(
                RowHolderMotionRightBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MotionViewHolder).bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) TYPE_LEFT else TYPE_RIGHT
    }

}
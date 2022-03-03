package com.iabcd.newtrail.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.iabcd.newtrail.databinding.RowHolderMotionLeftBinding
import com.iabcd.newtrail.databinding.RowHolderMotionRightBinding
import com.iabcd.newtrail.model.Holder
import com.iabcd.newtrail.util.MotionRocketView

class MotionHolderAdapter(
    private val items: List<Holder>,
    private val rocketView: MotionRocketView,
    private val onClick: (View, Holder, Int,MotionLayout,operation : Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_LEFT = 0
        private const val TYPE_RIGHT = 1

        const val OP_PLANET = 0
        const val OP_NEXT = 1
        const val OP_REPEAT = 2
    }

    inner class MotionViewHolder (private val mBinder : ViewBinding) : RecyclerView.ViewHolder(mBinder.root){

        fun bind(holder: Holder,position: Int){

            when(mBinder){

                is RowHolderMotionLeftBinding ->{
                    mBinder.rowGolderMotionTxtName.text = holder.name

                    mBinder.rowGolderMotionImagePlanet.setOnClickListener {
                        onClick(it,holder,position,mBinder.root, OP_PLANET)
                    }

                    mBinder.button10.setOnClickListener {
                        onClick(it,holder,position,mBinder.root, OP_NEXT)
                    }
                }
                is RowHolderMotionRightBinding ->{
                    mBinder.rowGolderMotionTxtName.text = holder.name

                    mBinder.rowGolderMotionImagePlanet.setOnClickListener {
                        onClick(it,holder,position,mBinder.root, OP_PLANET)
                    }

                    mBinder.button10.setOnClickListener {
                        onClick(it,holder,position,mBinder.root, OP_NEXT)
                    }
                }
                else -> throw Exception("Invalid row")
            }

            handlePlanetAnimationRecycle(position)

        }

        private fun handlePlanetAnimationRecycle(position: Int){

            when(position) {

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
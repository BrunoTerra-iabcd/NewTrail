package com.iabcd.newtrail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold
import com.iabcd.newtrail.databinding.RowHolderMotionLeftBinding
import com.iabcd.newtrail.databinding.RowHolderMotionRightBinding
import com.iabcd.newtrail.model.Holder

class MotionHolderAdapter(
    private val items: List<Holder>,
    private val onClick: (View, Holder, Int,MotionLayout) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_LEFT = 0
        const val TYPE_RIGHT = 1
    }

    inner class MotionHolderLeftViewHolder(private val mBinder: RowHolderMotionLeftBinding) :
        RecyclerView.ViewHolder(mBinder.root) {

            fun bind(holder: Holder,position: Int){

                mBinder.textView2.text = holder.name

                mBinder.imageView3.setOnClickListener {
                    onClick(it,holder,position,mBinder.root)
                }
            }

    }

    inner class MotionHolderRightViewHolder(private val mBinder: RowHolderMotionRightBinding) :
        RecyclerView.ViewHolder(mBinder.root) {

        fun bind(holder: Holder,position: Int){

            mBinder.textView2.text = holder.name

            mBinder.imageView3.setOnClickListener {
                onClick(it,holder,position,mBinder.root)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return when (viewType) {

            TYPE_LEFT -> MotionHolderLeftViewHolder(
                RowHolderMotionLeftBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> MotionHolderRightViewHolder(
                RowHolderMotionRightBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){

            is MotionHolderLeftViewHolder -> {
                holder.bind(items[position], position)
            }

            is MotionHolderRightViewHolder -> {
                holder.bind(items[position], position)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) TYPE_LEFT else TYPE_RIGHT
    }

}
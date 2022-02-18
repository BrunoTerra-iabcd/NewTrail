package com.iabcd.newtrail.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iabcd.newtrail.databinding.RowHolderBinding
import com.iabcd.newtrail.model.Holder

class HolderAdapter(private val items: List<Holder>, private val onClick : (View) -> Unit) :
    RecyclerView.Adapter<HolderAdapter.HolderViewHolder>() {

    inner class HolderViewHolder(private val mBinder: RowHolderBinding) :
        RecyclerView.ViewHolder(mBinder.root) {

        fun bind(holder: Holder, position: Int) {

            mBinder.rowHolderTxtName.text = holder.name
            setParentGravity(position)
            setCLickListener()

        }

        private fun setParentGravity(position: Int) {
            if (position % 2 == 0) {
                mBinder.root.gravity = Gravity.START
            } else {
                mBinder.root.gravity = Gravity.END
            }
        }

        private fun setCLickListener(){
            mBinder.rowHolderImage.setOnClickListener {
                onClick(it)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderViewHolder {
        return HolderViewHolder(
            RowHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HolderViewHolder, position: Int) {

        val item = items[position]

        holder.bind(item, position)

    }

    override fun getItemCount(): Int = items.size

}
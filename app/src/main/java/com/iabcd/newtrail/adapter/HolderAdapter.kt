package com.iabcd.newtrail.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.iabcd.newtrail.databinding.RowHolderBinding
import com.iabcd.newtrail.model.Holder
import com.iabcd.newtrail.util.RocketView
import kotlin.random.Random

class HolderAdapter(
    private val items: List<Holder>,
    private val rocketView : RocketView? = null,
    private val onClick: (View, Holder, Int) -> Unit
) : RecyclerView.Adapter<HolderAdapter.HolderViewHolder>() {

    inner class HolderViewHolder(private val mBinder: RowHolderBinding) :
        RecyclerView.ViewHolder(mBinder.root) {

        fun bind(holder: Holder, position: Int) {

            mBinder.rowHolderTxtName.text = holder.name
            setParentGravity(position)
            setCLickListener(holder, position)
            handleProps(position)
            handlePlanetScaling(mBinder.rowHolderImage,holder)
        }

        private fun setParentGravity(position: Int) {
            if (position % 2 == 0) {
                mBinder.rowHolderContainer.gravity = Gravity.START
            } else {
                mBinder.rowHolderContainer.gravity = Gravity.END
            }
        }

        private fun setCLickListener(holder: Holder,position : Int) {
            mBinder.rowHolderImage.setOnClickListener {
                onClick(it, holder,position)
            }
        }

        private fun handleProps(position: Int) {

            val random = Random.nextInt(0, 3)

            if (random == 0 || random == 1) {
                mBinder.rowHolderProp1.isVisible = true
                mBinder.rowHolderProp1.x += Random.nextInt(-100, 100)
            } else {
                mBinder.rowHolderProp1.isVisible = false
            }

            if (position > items.size / 3) {
                mBinder.rowHolderProp1.isVisible = false

                mBinder.rowHolderProp2.isVisible = random == 1
                mBinder.rowHolderProp3.isVisible = random == 2

            }else{
                mBinder.rowHolderProp2.isVisible = false
                mBinder.rowHolderProp3.isVisible = false
            }

        }

        private fun handlePlanetScaling(currentPlanet : ImageView,holder : Holder){

            rocketView?.getAttachedHolder()?.let {

                if (holder.name != it.name){
                    currentPlanet.scaleX = 1f
                    currentPlanet.scaleY = 1f
                }else{
                    currentPlanet.scaleX = RocketView.PLANET_SCALE_VALUE
                    currentPlanet.scaleY = RocketView.PLANET_SCALE_VALUE
                }
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
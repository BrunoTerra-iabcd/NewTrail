package com.iabcd.newtrail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iabcd.newtrail.adapter.HolderAdapter
import com.iabcd.newtrail.adapter.MotionHolderAdapter
import com.iabcd.newtrail.databinding.ActivityMotion3Binding
import com.iabcd.newtrail.model.Holder

class MotionActivity : AppCompatActivity() {

    private val mBinder by lazy {
        ActivityMotion3Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinder.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {

        mBinder.recyclerView2.apply {

            this.setHasFixedSize(false)
            this.layoutManager = LinearLayoutManager(this@MotionActivity,LinearLayoutManager.VERTICAL,true)
            this.adapter = MotionHolderAdapter(Holder.generateValues()){planetView, holder, position,motionLayout ->

                mBinder.recyclerView2.suppressLayout(true)

                mBinder.rocketView.animateToCoordinates(planetView,motionLayout,position,holder){
                    mBinder.recyclerView2.suppressLayout(false)
                }
            }

        }

        mBinder.recyclerView2.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mBinder.rocketView.y -= dy
            }
        })

    }
}
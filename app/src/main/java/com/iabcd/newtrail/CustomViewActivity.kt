package com.iabcd.newtrail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iabcd.newtrail.adapter.HolderAdapter
import com.iabcd.newtrail.databinding.ActivityCustomViewBinding
import com.iabcd.newtrail.model.Holder

class CustomViewActivity : AppCompatActivity() {
    private val mBinder by lazy {
        ActivityCustomViewBinding.inflate(layoutInflater)
    }

    //RecyclerView
    private lateinit var mAdapter: HolderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinder.root)
        initRecyclerView()
        initRocketView()
    }

    private fun initRecyclerView() {

        mAdapter = HolderAdapter(
            Holder.generateValues(),
            mBinder.activityCustomViewRocketView
        ) { planetView, holder, position ->

            handlePlanetClick(planetView,holder,position)
        }

        mBinder.activityCustomViewRecyclerView.apply {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(this@CustomViewActivity, LinearLayoutManager.VERTICAL, true)
            setHasFixedSize(true)
        }
    }

    private fun initRocketView(){

        mBinder.activityCustomViewRocketView.apply {
            this.attachRecyclerView(mBinder.activityCustomViewRecyclerView)
            this.attachScrollableItems(mBinder.activityCustomViewImageLand)
        }

    }

    private fun handlePlanetClick(planetView : View, holder: Holder, position : Int){
        mBinder.activityCustomViewRecyclerView.suppressLayout(true)

        mBinder.activityCustomViewRocketView.animateToCoordinates(
            planetView,
            position,
            holder
        ) {
            mBinder.activityCustomViewRecyclerView.suppressLayout(false)
            mBinder.activityCustomViewRecyclerView.post {
                mBinder.activityCustomViewRecyclerView.smoothScrollToPosition(position)
            }
        }
    }
}
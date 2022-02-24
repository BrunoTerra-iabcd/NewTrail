package com.iabcd.newtrail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun initRecyclerView() {

        mAdapter = HolderAdapter(
            Holder.generateValues(),
            mBinder.activityCustomViewRocketView
        ) { planetView, holder, position ->

            mBinder.activityCustomViewRecyclerView.suppressLayout(true)

            mBinder.activityCustomViewRocketView.animateToCoordinates(
                planetView,
                position,
                holder
            ) {

                mBinder.activityCustomViewRecyclerView.suppressLayout(false)



            }
        }

        mBinder.activityCustomViewRecyclerView.apply {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(this@CustomViewActivity, LinearLayoutManager.VERTICAL, true)
            setHasFixedSize(true)
        }

        mBinder.activityCustomViewRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mBinder.activityCustomViewRocketView.y -= dy
                mBinder.activityCustomViewImageLand.y -= dy
            }
        })
    }
}
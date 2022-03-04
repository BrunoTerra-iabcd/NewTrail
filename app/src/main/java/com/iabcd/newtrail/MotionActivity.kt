package com.iabcd.newtrail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iabcd.newtrail.adapter.MotionHolderAdapter
import com.iabcd.newtrail.databinding.ActivityMotion3Binding
import com.iabcd.newtrail.model.Holder
import com.iabcd.newtrail.util.motion.MotionClickResponse
import com.iabcd.newtrail.util.motion.MotionHandler

class MotionActivity : AppCompatActivity() {

    private val mBinder by lazy {
        ActivityMotion3Binding.inflate(layoutInflater)
    }
    private val motionHandler = MotionHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinder.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {

        mBinder.recyclerView2.apply {

            this.setHasFixedSize(false)
            this.layoutManager =
                LinearLayoutManager(this@MotionActivity, LinearLayoutManager.VERTICAL, true)
            this.adapter = MotionHolderAdapter(
                Holder.generateValues(),
                mBinder.rocketView
            ) { motionClickResponse : MotionClickResponse ->

                handleClickOperation(motionClickResponse)

            }
        }

        mBinder.recyclerView2.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mBinder.rocketView.y -= dy
                mBinder.imageView3.y -= dy
            }
        })
    }

    private fun handleClickOperation(
        response: MotionClickResponse
    ) {

        when (response.operationType) {

            MotionHolderAdapter.OP_PLANET -> {
                if (mBinder.rocketView.getCurrentAttachedPosition() == response.adapterPosition) return
                mBinder.recyclerView2.suppressLayout(true)

                mBinder.rocketView.animateToCoordinates(
                    response.clickedView,
                    response.adapterPosition,
                ) {
                    mBinder.recyclerView2.suppressLayout(false)
                    mBinder.recyclerView2.smoothScrollToPosition(response.adapterPosition)
                    motionHandler.scalePlanet(response.currentMotionLayout){
                        Log.i("Porsche", "handleClickOperation: hehe")
                    }
                }
            }

            MotionHolderAdapter.OP_NEXT -> {
                mBinder.recyclerView2.smoothScrollToPosition(response.adapterPosition + 1)

            }

            MotionHolderAdapter.OP_REPEAT -> {

            }
        }
    }
}
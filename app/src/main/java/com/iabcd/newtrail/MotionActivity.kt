package com.iabcd.newtrail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.iabcd.newtrail.adapter.HolderAdapter
import com.iabcd.newtrail.adapter.MotionHolderAdapter
import com.iabcd.newtrail.databinding.ActivityMotion3Binding
import com.iabcd.newtrail.databinding.RowHolderMotionLeftBinding
import com.iabcd.newtrail.databinding.RowHolderMotionRightBinding
import com.iabcd.newtrail.model.Holder
import com.iabcd.newtrail.util.PlanetView
import kotlinx.coroutines.delay

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
            this.layoutManager =
                LinearLayoutManager(this@MotionActivity, LinearLayoutManager.VERTICAL, true)
            this.adapter = MotionHolderAdapter(
                Holder.generateValues(),
                mBinder.rocketView
            ) { planetView, holder, position, motionLayout, operation ->

                handleClickOperation(operation, planetView, motionLayout, position, holder)

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
        operation: Int,
        planetView: View,
        motionRoot: MotionLayout,
        position: Int,
        holder: Holder
    ) {

        when (operation) {

            MotionHolderAdapter.OP_PLANET -> {
                mBinder.recyclerView2.suppressLayout(true)

                mBinder.rocketView.animateToCoordinates(
                    planetView,
                    motionRoot,
                    position,
                    holder
                ) {
                    mBinder.recyclerView2.suppressLayout(false)
                    mBinder.recyclerView2.smoothScrollToPosition(position)
                }
            }

            MotionHolderAdapter.OP_NEXT -> {
                mBinder.recyclerView2.smoothScrollToPosition(position + 1)

            }

            MotionHolderAdapter.OP_REPEAT -> {

            }
        }
    }
}
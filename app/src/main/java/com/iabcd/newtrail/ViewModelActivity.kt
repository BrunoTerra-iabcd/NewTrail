package com.iabcd.newtrail


import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewPropertyAnimator
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iabcd.newtrail.adapter.HolderAdapter
import com.iabcd.newtrail.databinding.ActivityMainBinding
import com.iabcd.newtrail.model.Holder
import com.iabcd.newtrail.viewmodel.RocketViewModel
import com.iabcd.newtrail.viewmodel.RocketViewModelFactory
import kotlinx.coroutines.flow.collect

class ViewModelActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Porsche"
    }

    //Layout
    private val mBinder by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //RecyclerView
    private lateinit var mAdapter: HolderAdapter

    //ViewModel
    private val rocketViewModel: RocketViewModel by viewModels {
        RocketViewModelFactory(
            mBinder.activityMainViewRocket.x.toInt(),
            mBinder.activityMainViewRocket.x.toInt()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinder.root)
        initRecyclerView()
        collectRocketChanges()
    }

    private fun initRecyclerView() {

        mAdapter = HolderAdapter(Holder.generateValues()) { planetView, holder, position ->

            val pointers = IntArray(2)
            planetView.getLocationOnScreen(pointers)

            rocketViewModel.updateRocketPositionFromCLick(pointers,position)
            rocketViewModel.currentHolder = holder
        }

        mBinder.activityMainRecyclerView.apply {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(this@ViewModelActivity, LinearLayoutManager.VERTICAL, true)
            setHasFixedSize(true)
        }

        mBinder.activityMainRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                mBinder.activityMainViewRocket.y -= dy
                mBinder.imageView2.y -= dy
                mBinder.imageView.y -= dy

                rocketViewModel.updateRocketPositionOnScroll(dx, dy)

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    rocketViewModel.isBeingScrolled = true
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    rocketViewModel.isBeingScrolled = false
                }
            }
        })
    }

    private fun collectRocketChanges() {

        lifecycleScope.launchWhenCreated {

            rocketViewModel.rocketCoordinates.collect {

                if (rocketViewModel.isBinded()) {
                    handleRocketMovement(it)
                }
            }
        }
    }

    private fun handleRocketMovement(coordinates: IntArray) {

        if (rocketViewModel.isBeingScrolled) {
            mBinder.activityMainViewRocket.x = coordinates[0].toFloat()
            mBinder.activityMainViewRocket.y = coordinates[1].toFloat()
        } else {
            val animator = mBinder.activityMainViewRocket.animate().apply {
                this.x(coordinates[0].toFloat())
                this.y(coordinates[1].toFloat())

                if (rocketViewModel.currentHolderPosition!! % 2 == 0) {
                    this.rotation(20f)
                } else {
                    this.rotation(-20f)
                }

                this.duration = 1000
            }

            handleRocketAnimation(animator)
        }
    }

    private fun handleRocketAnimation(animator: ViewPropertyAnimator) {

        animator.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                mBinder.activityMainRecyclerView.suppressLayout(true)
            }

            override fun onAnimationEnd(p0: Animator?) {
                mBinder.activityMainRecyclerView.suppressLayout(false)

                startActivity(Intent(this@ViewModelActivity, DigitalActivityActivity::class.java).apply {
                    this.putExtra(DigitalActivityActivity.HOLDER_KEY, rocketViewModel.currentHolder!!.name)
                })
            }

            override fun onAnimationCancel(p0: Animator?) {
                mBinder.activityMainRecyclerView.suppressLayout(false)
            }

            override fun onAnimationRepeat(p0: Animator?) {
                mBinder.activityMainRecyclerView.suppressLayout(true)
            }
        })
    }
}
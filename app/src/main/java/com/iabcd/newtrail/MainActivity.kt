package com.iabcd.newtrail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class MainActivity : AppCompatActivity() {

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

        mAdapter = HolderAdapter(generateValues()) {

            val pointers = IntArray(2)
            it.getLocationOnScreen(pointers)

            Log.i(TAG, "OFFSET X: ${pointers[0]}")
            Log.i(TAG, "OFFSET Y: ${pointers[1]}")

            rocketViewModel.updateRocketPositionFromCLick(pointers)

        }

        mBinder.activityMainRecyclerView.apply {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, true)
            setHasFixedSize(true)
        }

        mBinder.activityMainRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                Log.i(TAG, "onScrolled X: $dx")
                Log.i(TAG, "onScrolled Y: $dy")

                if (rocketViewModel.isBinded())
                rocketViewModel.updateRocketPositionOnScroll(dx,dy)
            }
        })
    }

    private fun generateValues(): List<Holder> {
        val items = mutableListOf<Holder>()
        for (i in 0..45) {
            items.add(Holder("Planeta $i"))
        }
        return items
    }

    private fun collectRocketChanges(){

        lifecycleScope.launchWhenCreated {

            rocketViewModel.rocketCoordinates.collect {
                mBinder.activityMainViewRocket.x = it[0].toFloat()
                mBinder.activityMainViewRocket.y = it[1].toFloat()
            }
        }
    }
}
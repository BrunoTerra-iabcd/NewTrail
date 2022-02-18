package com.iabcd.newtrail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iabcd.newtrail.adapter.HolderAdapter
import com.iabcd.newtrail.databinding.ActivityMainBinding
import com.iabcd.newtrail.model.Holder

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Porsche"
    }

    private val mBinder by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mAdapter: HolderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinder.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {

        mAdapter = HolderAdapter(generateValues()) {

            val pointers = IntArray(2)
            it.getLocationOnScreen(pointers)

            Log.i(TAG, "OFFSET X: ${pointers[0]}")
            Log.i(TAG, "OFFSET Y: ${pointers[1]}")

            moveRocket(pointers)

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

                updateRocketPosition(dx, dy)
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

    private fun moveRocket(coordinates: IntArray) {
        mBinder.activityMainViewRocket.x = coordinates[0].toFloat()
        mBinder.activityMainViewRocket.y = coordinates[1].toFloat()
    }

    private fun updateRocketPosition(offSetX: Int, offSetY: Int) {
        mBinder.activityMainViewRocket.x -= offSetX
        mBinder.activityMainViewRocket.y -= offSetY
    }
}
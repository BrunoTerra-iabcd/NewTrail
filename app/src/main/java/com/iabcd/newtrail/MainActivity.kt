package com.iabcd.newtrail

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.Hold
import com.iabcd.newtrail.adapter.HolderAdapter
import com.iabcd.newtrail.databinding.ActivityMainBinding
import com.iabcd.newtrail.model.Holder

class MainActivity : AppCompatActivity() {

    private val mBinder by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mAdapter: HolderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinder.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){

        mAdapter = HolderAdapter(generateValues())

        mBinder.activityMainRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,true)
            setHasFixedSize(true)
        }
    }

    private fun generateValues() : List<Holder>{

        val items = mutableListOf<Holder>()

        for (i in 0..45){
            items.add(Holder("Planeta $i"))
        }

        return items

    }
}
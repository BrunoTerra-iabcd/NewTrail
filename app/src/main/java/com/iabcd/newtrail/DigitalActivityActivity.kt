package com.iabcd.newtrail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iabcd.newtrail.databinding.ActivityHolderBinding
import com.iabcd.newtrail.databinding.ActivityMainBinding

class DigitalActivityActivity : AppCompatActivity() {

    companion object {
        const val HOLDER_KEY = "holder_key"
    }

    //Layout
    private val mBinder by lazy {
        ActivityHolderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinder.root)
        retrieveHolder()
    }

    private fun retrieveHolder() {

        intent.extras?.getString(HOLDER_KEY)?.let {
            mBinder.activityHolderTxtTarget.text = it
        }
    }
}
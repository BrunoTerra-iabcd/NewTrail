package com.iabcd.newtrail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iabcd.newtrail.databinding.ActivityChoiceBinding

class ChoiceActivity : AppCompatActivity() {

    private val mBinder by lazy {
        ActivityChoiceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinder.root)

        mBinder.button.setOnClickListener {
            startActivity(Intent(this,ViewModelActivity::class.java))
        }

        mBinder.button2.setOnClickListener {
            startActivity(Intent(this, CustomViewActivity::class.java))
        }

        mBinder.button3.setOnClickListener {
            startActivity(Intent(this,ComposeActivity::class.java))
        }

    }
}
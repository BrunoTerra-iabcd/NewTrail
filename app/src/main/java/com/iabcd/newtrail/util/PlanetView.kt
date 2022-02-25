package com.iabcd.newtrail.util

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.iabcd.newtrail.R
import com.iabcd.newtrail.databinding.CustomPlanetViewBinding

class PlanetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    private val mBinder = CustomPlanetViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }

    fun setPlanetName(name : String){
        mBinder.customPlanetViewTxtPlanetName.text = name
    }

    fun setPlanetAxis(axis : String){
        mBinder.customPlanetViewTxtPlanetAxis.text = axis
    }

    private fun setLayout(attrs: AttributeSet?) {

        attrs?.let {

            val attributes = context.obtainStyledAttributes(it, R.styleable.PlanetView)
            bindXmlToBinder(attributes)
            attributes.recycle()
        }
    }
    private fun bindXmlToBinder(attrs: TypedArray) {

        mBinder.customPlanetViewImagePlanet.setImageResource(
            attrs.getResourceId(
                R.styleable.PlanetView_c_planet_image,
                0
            )
        )

        mBinder.customPlanetViewTxtPlanetName.text =
            attrs.getString(R.styleable.PlanetView_c_planet_planetName)
        mBinder.customPlanetViewTxtPlanetAxis.text =
            attrs.getString(R.styleable.PlanetView_c_planet_planetAxis)

        adjustPlanetSize(attrs.getDimension(R.styleable.PlanetView_c_planet_planetSize,-1f))

    }

    private fun adjustPlanetSize(size : Float){

        if (size <0) return

        val params = mBinder.customPlanetViewImagePlanet.layoutParams
        params.height = size.toInt()
        params.width = size.toInt()
        mBinder.customPlanetViewImagePlanet.layoutParams = params

    }
}
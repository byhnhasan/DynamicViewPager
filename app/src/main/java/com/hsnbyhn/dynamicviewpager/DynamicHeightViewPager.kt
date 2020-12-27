package com.hsnbyhn.dynamicviewpager

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

/**
 * Created by hasanbayhan on 27.12.2020
 **/

class DynamicHeightViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {


    private var listener: PagerListener? = null

    fun setListener(listener: PagerListener) {
        this.listener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val currentView = (adapter?.instantiateItem(this, currentItem) as Fragment).view
        if (currentView == null) {
            for (i in 0..childCount) {
                val child = (adapter?.instantiateItem(this, i) as Fragment).view
                child?.measure(
                    widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                );

                super.onMeasure(
                    widthMeasureSpec, MeasureSpec.makeMeasureSpec(
                        MeasureSpec.makeMeasureSpec(
                            child?.measuredHeight
                                ?: 0, MeasureSpec.EXACTLY
                        ), MeasureSpec.EXACTLY
                    )
                )
            }
        } else {
            var height = 0
            currentView.measure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )
            currentView.measuredHeight.let {
                if (it > height) {
                    height = it
                }
            }
            listener?.onMeasured(height)
            super.onMeasure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
            )
        }
    }
}

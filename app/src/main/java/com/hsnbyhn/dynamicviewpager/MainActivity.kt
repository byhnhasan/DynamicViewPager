package com.hsnbyhn.dynamicviewpager

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.hsnbyhn.dynamicviewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PagerListener {

    private var binding: ActivityMainBinding? = null
    private var animStarted = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setViewPager()
        setContentView(binding?.root)
    }

    private fun setViewPager() {
        val adapter = ViewPagerAdapter(fm = supportFragmentManager)
        binding?.apply {
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(this.viewPager)
            viewPager.setListener(this@MainActivity)
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    viewPager.requestLayout()
                    animStarted = false
                }

            })
        }
    }

    override fun onMeasured(height: Int) {
        if(!animStarted) {
            animStarted = true
            val anim = ValueAnimator.ofInt(binding?.pagerContainer?.measuredHeight ?: 0 , height).apply {
                addUpdateListener {
                    val value = it.animatedValue
                    val layoutParams = binding?.pagerContainer?.layoutParams
                    layoutParams?.height = value as Int
                    binding?.pagerContainer?.layoutParams = layoutParams
                    binding?.pagerContainer?.requestLayout()
                }
                duration = 500
                start()

            }
        }
    }

}
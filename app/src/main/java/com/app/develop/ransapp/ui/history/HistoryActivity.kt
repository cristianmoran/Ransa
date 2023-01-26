package com.app.develop.ransapp.ui.history

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.app.develop.ransapp.R
import com.app.develop.ransapp.base.BaseActivity
import com.app.develop.ransapp.databinding.ActivityHistoryBinding
import com.app.develop.ransapp.ui.history.adapter.HistoryPagerAdapter

class HistoryActivity : BaseActivity() {


    companion object {
        var TAB_ONE_POSITION = 0
        var TAB_TWO_POSITION = 1
        var TAB_THREE_POSITION = 2
        var TAB_DEFAULT_POSITION = 3
    }

    lateinit var binding: ActivityHistoryBinding
    private var fragment: Fragment? = null
    private lateinit var mPagerAdapter: HistoryPagerAdapter
    private var mCurrentTabPosition = 0

    override fun initObserver() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViewNavigation()
    }


    private fun initViewNavigation() {
        mPagerAdapter = HistoryPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = mPagerAdapter
        binding.viewPager.offscreenPageLimit = 4
        binding.bottomNavigationView.itemIconTintList = null
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.oneFragment -> {
                    initOneFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.twoFragment -> {
                    initTwoFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.threeFragment -> {
                    initThreeFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.defaultFragment -> {
                    initDefaultFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
        // initSetDataCoinPurseFragment()
    }



    private fun initOneFragment() {
        mCurrentTabPosition = TAB_ONE_POSITION
        binding.viewPager.currentItem = mCurrentTabPosition
        fragment = mPagerAdapter.getItem(TAB_ONE_POSITION)
    }

    private fun initTwoFragment() {
        mCurrentTabPosition = TAB_TWO_POSITION
        binding.viewPager.currentItem = mCurrentTabPosition
        fragment = mPagerAdapter.getItem(TAB_TWO_POSITION)
    }

    private fun initThreeFragment() {
        mCurrentTabPosition = TAB_THREE_POSITION
        binding.viewPager.currentItem = mCurrentTabPosition
        fragment = mPagerAdapter.getItem(TAB_THREE_POSITION)
    }

    private fun initDefaultFragment() {
        mCurrentTabPosition = TAB_DEFAULT_POSITION
        binding.viewPager.currentItem = mCurrentTabPosition
        fragment = mPagerAdapter.getItem(TAB_DEFAULT_POSITION)
    }

}
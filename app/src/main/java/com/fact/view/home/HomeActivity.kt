package com.fact.view.home

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.fact.R
import com.fact.databinding.ActivityHomeBinding
import com.fact.view.base.BaseActivity
import com.fact.view.home.fragment.HomeFragment
import com.fact.viewmodel.HomeViewModel
import com.google.android.material.appbar.AppBarLayout
import javax.inject.Inject

/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_home
    override val viewModel: HomeViewModel
        get() = homeViewModel

    //Field injection
    @Inject
    lateinit var homeViewModel: HomeViewModel
    private var binding: ActivityHomeBinding? = null
    private var isCollapsed = false
    var onTimeout = false
    var onUnknownError = false
    var onNetworkError = false
    var onUnknownErrorCode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        behaviourNavigation()
        if (savedInstanceState == null)
            displayFragment()
    }

    /**
     * Display home fragment
     */
    private fun displayFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val homeFragment = HomeFragment.newInstance()
        homeFragment.homeViewModel = homeViewModel
        intent?.let {
            val bundle = Bundle()
            bundle.putBoolean("isError", intent.getBooleanExtra("isError", false))
            homeFragment.arguments = bundle
        }
        transaction.replace(R.id.frameLayout, homeFragment)
        transaction.commit()
    }

    /**
     * Detect scroll behaviour when user scroll data
     */
    private fun behaviourNavigation() {
        binding?.appBarLayout?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            isCollapsed = when {
                Math.abs(verticalOffset) == binding?.appBarLayout?.totalScrollRange -> {
                    // Collapsed
                    true
                }
                verticalOffset == 0 -> {
                    // Expanded
                    false
                }
                else -> {
                    false
                    // Somewhere in between
                }
            }
        })
    }


    /**
     * Detect if toll bar hide when user click system back press
     */
    override fun onBackPressed() {
        if (isCollapsed)
            binding?.appBarLayout?.setExpanded(true, true)
        else
            super.onBackPressed()
    }

    /**
     * Save title value
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("title", "${binding?.toolBar?.title}")
        super.onSaveInstanceState(outState)
    }

    /**
     * Get title value and display
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            binding?.toolBar?.title = savedInstanceState.getString("title")
        }
    }
}

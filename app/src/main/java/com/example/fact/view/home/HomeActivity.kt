package com.example.fact.view.home

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.example.fact.R
import com.example.fact.databinding.ActivityHomeBinding
import com.example.fact.view.base.BaseActivity
import com.example.fact.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
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

    @Inject
    lateinit var homeViewModel: HomeViewModel
    var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                binding?.viewFlipper?.displayedChild = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_info -> {
                binding?.viewFlipper?.displayedChild = 1
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}

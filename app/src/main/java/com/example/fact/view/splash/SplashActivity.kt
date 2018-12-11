package com.example.fact.view.splash

import androidx.databinding.library.baseAdapters.BR
import com.example.fact.R
import com.example.fact.databinding.ActivitySplashBinding
import com.example.fact.view.base.BaseActivity
import com.example.fact.viewmodel.SplashViewModel
import javax.inject.Inject

/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = splashViewModel

    //Feild injection
    @Inject
    lateinit var splashViewModel: SplashViewModel

}

package com.example.fact.view.home.fragment


import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.example.fact.R
import com.example.fact.databinding.FragmentInfoBinding
import com.example.fact.view.base.BaseFragment
import com.example.fact.viewmodel.HomeViewModel
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
class InfoFragment : BaseFragment<FragmentInfoBinding, HomeViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_info
    override val viewModel: HomeViewModel
        get() = homeViewModel

    @Inject
    lateinit var homeViewModel: HomeViewModel

}

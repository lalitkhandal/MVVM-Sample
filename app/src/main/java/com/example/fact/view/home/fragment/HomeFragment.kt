package com.example.fact.view.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.fact.R
import com.example.fact.databinding.FragmentHomeBinding
import com.example.fact.global.isNetworkConnected
import com.example.fact.global.showSnackBar
import com.example.fact.model.FactResponse
import com.example.fact.navigator.HomeNavigator
import com.example.fact.view.home.HomeActivity
import com.example.fact.view.home.adapter.HomeAdapter
import com.example.fact.viewmodel.HomeViewModel


/**
 * A simple [Fragment] subclass.
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
class HomeFragment : Fragment(), HomeNavigator {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    var homeActivity: HomeActivity? = null
    private var homeAdapter: HomeAdapter? = null
    private var binding: FragmentHomeBinding? = null
    var homeViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeActivity = activity as HomeActivity
        homeViewModel?.navigator = this
        homeAdapter = HomeAdapter()
        binding?.factRecyclerView?.adapter = homeAdapter
        binding?.factSwipeRefreshLayout?.setOnRefreshListener {
            getFactData()
        }
        if (homeViewModel?.factRowsListResponse?.value == null)
            getFactData()
        observeData()
    }

    /**
     * Get data from server if network available
     */
    private fun getFactData() {
        isNetworkConnected {
            when {
                it -> {
                    homeViewModel?.getFactData()
                }
                else -> {
                    onRefresh(false)
                    onNetworkError()
                }
            }
        }
    }

    /**
     * Subscribe observe so we detect data when any changes in live data
     */
    private fun observeData() {
        homeViewModel?.factRowsListResponse?.observe(this, Observer<FactResponse> { it ->
            it?.let {
                homeAdapter?.addItems(homeViewModel!!.checkData(it.rows))
                homeActivity?.idlingResource?.setIdleState(true)
            }
        })
    }

    override fun onRefresh(isRefresh: Boolean) {
        binding?.factSwipeRefreshLayout?.isRefreshing = isRefresh
        homeActivity?.idlingResource?.setIdleState(false)
    }

    override fun onUnknownErrorCode(statusCode: Int, errorMessage: String) {
        binding?.factRecyclerView.showSnackBar(errorMessage)
        homeActivity?.idlingResource?.setIdleState(true)
    }

    override fun onUnknownError(errorMessage: String) {
        binding?.factRecyclerView.showSnackBar(errorMessage)
        homeActivity?.idlingResource?.setIdleState(true)
    }

    override fun onTimeout() {
        binding?.factRecyclerView.showSnackBar(activity?.getString(R.string.requestFailed))
        homeActivity?.idlingResource?.setIdleState(true)
    }

    override fun onNetworkError() {
        binding?.factRecyclerView.showSnackBar(activity?.getString(R.string.noInternet))
        homeActivity?.idlingResource?.setIdleState(true)
    }
}

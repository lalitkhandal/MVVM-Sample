package com.fact.view.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.fact.R
import com.fact.databinding.FragmentHomeBinding
import com.fact.global.isNetworkConnected
import com.fact.global.showSnackBar
import com.fact.model.FactResponse
import com.fact.navigator.HomeNavigator
import com.fact.view.home.HomeActivity
import com.fact.view.home.adapter.HomeAdapter
import com.fact.viewmodel.HomeViewModel


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

    private var homeActivity: HomeActivity? = null
    private var homeAdapter: HomeAdapter? = null
    private var binding: FragmentHomeBinding? = null
    var homeViewModel: HomeViewModel? = null
    private var isError = false

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
        arguments?.let {
            isError = it.getBoolean("isError")
        }

        homeActivity = activity as HomeActivity
        homeViewModel?.navigator = this
        homeAdapter = HomeAdapter()
        binding?.factRecyclerView?.adapter = homeAdapter
        binding?.factSwipeRefreshLayout?.setOnRefreshListener {
            getFactData()
        }

        observeData()
        if (homeViewModel?.factRowsListResponse?.value == null)
            getFactData()
        if (isError)
            homeViewModel?.cancelRequest()
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
            }
        })
    }

    override fun onRefresh(isRefresh: Boolean) {
        binding?.factSwipeRefreshLayout?.isRefreshing = isRefresh
    }

    override fun onUnknownErrorCode(statusCode: Int, errorMessage: String) {
        binding?.factRecyclerView.showSnackBar(errorMessage)
        homeActivity?.onUnknownErrorCode = true
    }

    override fun onUnknownError(errorMessage: String) {
        binding?.factRecyclerView.showSnackBar(errorMessage)
        homeActivity?.onUnknownError = true
    }

    override fun onTimeout() {
        binding?.factRecyclerView.showSnackBar(activity?.getString(R.string.requestFailed))
        homeActivity?.onTimeout = true
    }

    override fun onNetworkError() {
        binding?.factRecyclerView.showSnackBar(activity?.getString(R.string.noInternet))
        homeActivity?.onNetworkError = true
    }
}

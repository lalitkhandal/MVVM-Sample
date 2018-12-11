package com.example.fact.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fact.databinding.RowHomeBinding
import com.example.fact.model.FactRows
import com.example.fact.view.base.BaseViewHolder
import com.example.fact.viewmodel.AdapterHomeViewModel

class HomeAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    private var factRowsList: MutableList<FactRows> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: RowHomeBinding = RowHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    fun addItems(factRowsList: List<FactRows>) {
        this.factRowsList = ArrayList()
        this.factRowsList.addAll(factRowsList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return factRowsList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ItemViewHolder(binding: RowHomeBinding) : BaseViewHolder(binding.root) {
        var rowBinding = binding
        private var adapterHomeViewModel: AdapterHomeViewModel? = null
        override fun onBind(position: Int) {
            adapterHomeViewModel = AdapterHomeViewModel(factRowsList[position])
            rowBinding.viewModel = adapterHomeViewModel
            rowBinding.executePendingBindings()
        }
    }
}
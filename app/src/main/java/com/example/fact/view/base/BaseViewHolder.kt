package com.example.fact.view.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Lalit on 08/13/18.
 */

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int)
}

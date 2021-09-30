package com.geekbrains.pictureoftheday.view.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Maxim Zamyatin on 29.09.2021
 */


abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: Data)
}
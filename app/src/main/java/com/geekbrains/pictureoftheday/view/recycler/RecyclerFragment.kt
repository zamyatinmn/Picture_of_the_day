package com.geekbrains.pictureoftheday.view.recycler

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.pictureoftheday.databinding.FragmentRecyclerBinding
import com.geekbrains.pictureoftheday.view.ViewBindingFragment


/**
 * Created by Maxim Zamyatin on 29.09.2021
 */


class RecyclerFragment :
    ViewBindingFragment<FragmentRecyclerBinding>(FragmentRecyclerBinding::inflate) {
    companion object {
        fun newInstance() = RecyclerFragment()
    }

    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: MutableList<Data> = ArrayList()
        for (i in 1..20) {
            if (i % 3 != 0) {
                data.add(Data("Hello $i", RecyclerAdapter.TYPE_DEFAULT))
            } else {
                data.add(Data("Hello $i", RecyclerAdapter.TYPE_ANOTHER))
            }
        }
        data.add(0, Data("Header1", RecyclerAdapter.TYPE_HEADER))
        data.add(3, Data("Header2", RecyclerAdapter.TYPE_HEADER))
        data.add(7, Data("Header3", RecyclerAdapter.TYPE_HEADER))
        data.add(14, Data("Header4", RecyclerAdapter.TYPE_HEADER))
        val adapter = RecyclerAdapter(
            object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            },
            data
        )
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(ui.root)
        ui.root.adapter = adapter
        ui.root.addItemDecoration(StickyHeaderItemDecorator(adapter))
    }
}
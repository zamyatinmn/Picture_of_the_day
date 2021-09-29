package com.geekbrains.pictureoftheday.view.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.RecyclerItemAnotherBinding
import com.geekbrains.pictureoftheday.databinding.RecyclerItemBinding
import com.geekbrains.pictureoftheday.databinding.RecyclerItemHeaderBinding


/**
 * Created by Maxim Zamyatin on 29.09.2021
 */


class RecyclerAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var dragListener: OnStartDragListener,
    private var data: MutableList<Data>
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    companion object {
        const val TYPE_DEFAULT = 0
        const val TYPE_HEADER = 1
        const val TYPE_ANOTHER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding =
                    RecyclerItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                HeaderItemViewHolder(binding.root)
            }
            TYPE_ANOTHER -> {
                val binding =
                    RecyclerItemAnotherBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                AnotherItemViewHolder(binding.root)
            }
            else -> {
                val binding =
                    RecyclerItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                DefaultItemViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class HeaderItemViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        override fun bind(data: Data) {
            RecyclerItemHeaderBinding.bind(itemView).apply {
                text.text = data.text
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class DefaultItemViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        override fun bind(data: Data) {
            RecyclerItemBinding.bind(itemView).apply {
                text.text = data.text
                image.setImageResource(R.drawable.ic_favourite_menu)
                var vis = false
                text.setOnClickListener {
                    vis = !vis
                    if (vis) surprise.visibility = View.GONE else surprise.visibility = View.VISIBLE
                    toggleText()
                }
                add.setOnClickListener { addItem() }
                delete.setOnClickListener { removeItem() }
                burger.setOnTouchListener { v, event ->
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        dragListener.onStartDrag(this@DefaultItemViewHolder)
                    }
                    false
                }
            }
        }

        private fun toggleText() {
            notifyItemChanged(layoutPosition)
        }

//        private fun moveUp() {
//            layoutPosition.takeIf { it > 1 }?.also {
//                data.removeAt(it).apply {
//                    data.add(it - 1, this)
//                }
//                notifyItemMoved(it, it - 1)
//            }
//        }
//
//        private fun moveDown() {
//            layoutPosition.takeIf { it < itemCount - 1 }?.also {
//                data.removeAt(it).apply {
//                    data.add(it + 1, this)
//                }
//                notifyItemMoved(it, it + 1)
//            }
//        }

        private fun addItem() {
            data.add(layoutPosition, Data("Generated", TYPE_DEFAULT))
            notifyItemInserted(layoutPosition)
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class AnotherItemViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        override fun bind(data: Data) {
            RecyclerItemAnotherBinding.bind(itemView).apply {
                text.text = data.text
                var vis = false
                text.setOnClickListener {
                    vis = !vis
                    if (vis) surprise.visibility = View.GONE else surprise.visibility = View.VISIBLE
                }
                add.setOnClickListener { addItem() }
                delete.setOnClickListener { removeItem() }
                burger.setOnTouchListener { v, event ->
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        dragListener.onStartDrag(this@AnotherItemViewHolder)
                    }
                    false
                }
            }
        }

        private fun addItem() {
            data.add(layoutPosition, Data("Generated", TYPE_DEFAULT))
            notifyItemInserted(layoutPosition)
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }
}
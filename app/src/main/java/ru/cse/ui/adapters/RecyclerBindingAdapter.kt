package ru.cse.ui.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.AbstractList
import java.util.ArrayList


class RecyclerBindingAdapter<T>(private var holderLayout: Int, private var variableId: Int, items: List<T>) : RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder>() {
    private var items = ArrayList<T>()
    private var onItemClickListener: OnItemClickListener<T>? = null

    init {
        this.holderLayout = holderLayout
        this.variableId = variableId
        this.items = items as ArrayList<T>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerBindingAdapter.BindingHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(holderLayout, parent, false)

        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerBindingAdapter.BindingHolder, position: Int) {
        val item = items[position]
        holder.binding!!.root.setOnClickListener { view ->
            if (onItemClickListener != null)
                onItemClickListener!!.onItemClick(position, item)
        }
        holder.binding.setVariable(variableId, item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener<T> {
        fun onItemClick(position: Int, item: T)
    }

    class BindingHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(view)
    }
}

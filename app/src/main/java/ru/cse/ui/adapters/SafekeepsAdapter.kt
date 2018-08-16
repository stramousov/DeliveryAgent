package ru.cse.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.cse.R
import ru.cse.app.models.Safekeep
import ru.cse.utils.DateConverter


class SafekeepAdapter : RecyclerView.Adapter<SafekeepAdapter.SafekeepViewHolder>() {
    private var safekeeps: List<Safekeep> = emptyList()

    fun setSafekeeps(safekeeps: List<Safekeep>) {
        this.safekeeps = safekeeps
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SafekeepViewHolder {
        val view = from(parent.context).inflate(R.layout.safekeep_card_item, parent, false)
        return SafekeepViewHolder(view)
    }

    override fun getItemCount(): Int = safekeeps.size

    override fun onBindViewHolder(holder: SafekeepViewHolder, position: Int) {
        holder.bind(safekeeps[position])
    }

    class SafekeepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val number: TextView = itemView.findViewById(R.id.safekeepDocNumber)
        private val date: TextView = itemView.findViewById(R.id.safekeepDocDate)
        private val description: TextView = itemView.findViewById(R.id.safekeepDocDescription)

        fun bind(safekeep: Safekeep) {
            number.text = safekeep.Number
            date.text = DateConverter.convert(safekeep.Date.time)
            description.text = safekeep.Description
        }
    }
}
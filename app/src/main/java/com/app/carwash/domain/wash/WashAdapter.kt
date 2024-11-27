package com.app.carwash.domain.wash

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.carwash.R
import com.app.carwash.interfaces.IClickListener

class WashAdapter(private val washes: List<Wash>, private val clickListener: IClickListener ): Adapter<WashAdapter.WashViewHolder>() {
    class WashViewHolder(view: View, private val clickListener: IClickListener): ViewHolder(view), OnClickListener {
        private lateinit var id: String

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener.onItemClick(id)
        }

        fun bind(wash: Wash) {
            id = wash.id
            itemView.findViewById<TextView>(R.id.client_name_value).text = wash.clientName
            itemView.findViewById<TextView>(R.id.vehicle_value).text = wash.vehicleModel
            itemView.findViewById<TextView>(R.id.date_value).text = wash.washDate.toString()
            var valueString = wash.value.toString()
            val splittedValue = valueString.split(".")
            if (splittedValue[1].length == 1) {
                valueString += "0"
            }
            itemView.findViewById<TextView>(R.id.wash_value_value).text = valueString
                .replace(".", ",")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WashViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wash_card, parent, false)
        return  WashViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int = washes.size

    override fun onBindViewHolder(holder: WashViewHolder, position: Int) {
        val wash = washes[position]
        holder.bind(wash)
    }
}
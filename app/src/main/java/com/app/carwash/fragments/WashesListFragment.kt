package com.app.carwash.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.carwash.R
import com.app.carwash.domain.wash.WashActivity
import com.app.carwash.domain.wash.WashAdapter
import com.app.carwash.domain.wash.washes
import com.app.carwash.interfaces.IClickListener

class WashesListFragment : Fragment(), IClickListener {
    private lateinit var washesList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.washes_list, container, false)
        washesList = view.findViewById(R.id.washes_list)
        washesList.layoutManager = LinearLayoutManager(this.context)
        washesList.adapter = WashAdapter(washes, this)
        return view
    }

    override fun onItemClick(id: String) {
        val washIntent = Intent(this.context, WashActivity::class.java).apply {
            putExtra("washId", id)
        }
        startActivity(washIntent)
    }
}
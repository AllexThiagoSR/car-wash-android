package com.app.carwash.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.carwash.domain.wash.CreateNewWashActivity
import com.app.carwash.R
import com.app.carwash.domain.wash.WashActivity
import com.app.carwash.domain.wash.WashAdapter
import com.app.carwash.domain.wash.washes
import com.app.carwash.interfaces.IClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WashesListFragment : Fragment(), IClickListener, View.OnClickListener {
    private lateinit var washesList: RecyclerView
    private lateinit var addNewWashButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.washes_list, container, false)
        washesList = view.findViewById(R.id.washes_list)
        washesList.layoutManager = LinearLayoutManager(this.context)
        addNewWashButton = view.findViewById(R.id.add_new_wash_button)
        addNewWashButton.setOnClickListener(this)
        return view
    }

    override fun onStart() {
        super.onStart()
        washesList.adapter = WashAdapter(washes, this)
    }

    override fun onItemClick(id: String) {
        val washIntent = Intent(this.context, WashActivity::class.java).apply {
            putExtra("washId", id)
        }
        startActivity(washIntent)
    }

    override fun onClick(v: View?) {
        val addNewWashIntent = Intent(this.context, CreateNewWashActivity::class.java)
        startActivity(addNewWashIntent)
    }
}
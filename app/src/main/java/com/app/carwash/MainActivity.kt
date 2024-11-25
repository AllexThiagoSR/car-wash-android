package com.app.carwash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.carwash.domain.wash.WashActivity
import com.app.carwash.domain.wash.WashAdapter
import com.app.carwash.domain.wash.washes
import com.app.carwash.interfaces.IClickListener

class MainActivity : AppCompatActivity(), IClickListener {
    private val washesList: RecyclerView by lazy { findViewById(R.id.washes_list) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        washesList.layoutManager = LinearLayoutManager(this)
        washesList.adapter = WashAdapter(washes, this)
    }

    override fun onItemClick(position: Int) {
        val washIntent = Intent(this, WashActivity::class.java).apply {
            putExtra("washId", position)
        }
        startActivity(washIntent)
    }
}
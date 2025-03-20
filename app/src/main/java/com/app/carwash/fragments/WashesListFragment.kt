package com.app.carwash.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.carwash.domain.wash.CreateNewWashActivity
import com.app.carwash.R
import com.app.carwash.domain.wash.Wash
import com.app.carwash.domain.wash.WashActivity
import com.app.carwash.domain.wash.WashAdapter
import com.app.carwash.domain.wash.WashApiService
import com.app.carwash.interfaces.IClickListener
import com.app.carwash.requester
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WashesListFragment : Fragment(), IClickListener, View.OnClickListener {
    private lateinit var washesList: RecyclerView
    private lateinit var addNewWashButton: FloatingActionButton
    private var washes: MutableList<Wash> = emptyList<Wash>().toMutableList()

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
        val onClickListener = this
        super.onStart()
        val washApiService = requester.create(WashApiService::class.java)
        val callGetWashes = washApiService.getWashes("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjkwZDU5NTY3LWJiODQtNDg0ZC04YTlhLTgxNDQyNDI0MWNlOCIsIm5hbWUiOiJqdWxpbWFyQGdtYWlsLmNvbSIsImF1ZCI6Im1hbmFnZXIiLCJpc3MiOiJpbnRlcm4tYXBpIn0.xpTR3iff5PBwlijebdhwYrJ3NJkaPSCclTykuCfd3f4")
        callGetWashes.enqueue(object : Callback<List<Wash>> {
            override fun onResponse(call: Call<List<Wash>>, response: Response<List<Wash>>) {
                if (response.isSuccessful && response.code() == 200) {
                    washes = response.body()!!.toMutableList()
                    washesList.adapter = WashAdapter(washes.toMutableList(), onClickListener)
                    Log.i("ApiResponse", response.body().toString() + "Body")
                }
            }

            override fun onFailure(call: Call<List<Wash>>, t: Throwable) {
                Log.e("ApiError", t.toString())
            }
        })

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
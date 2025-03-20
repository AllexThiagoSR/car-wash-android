package com.app.carwash

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.app.carwash.fragments.IncomeReportFragment
import com.app.carwash.fragments.WashesListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val washesListFragment = WashesListFragment()
    private val incomeReportFragment = IncomeReportFragment()
    private val bottomMenu by lazy { findViewById<BottomNavigationView>(R.id.main_bottom_menu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        changeFragment(washesListFragment)
        bottomMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.washes_menu_item -> {
                    changeFragment(washesListFragment)
                    true
                }
                R.id.income_report_menu_item -> {
                    changeFragment(incomeReportFragment)
                    true
                }
                else -> true
            }
        }

    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_view, fragment)
            .commit()
    }
}
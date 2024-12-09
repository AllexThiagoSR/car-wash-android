package com.app.carwash.domain.wash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.carwash.MainActivity
import com.app.carwash.R
import java.util.Date

class CreateNewWashActivity : AppCompatActivity() {
    private val backToMainButton: Button by lazy { findViewById(R.id.back_to_main_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_new_wash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addNewWash(
            "Allex",
            "Mustang",
            "2024-12-11",
            120.0,
            "Carro que foi lavado",
        )
        backToMainButton.setOnClickListener {
            val mainActivityIntent = Intent(baseContext, MainActivity::class.java)
            startActivity(mainActivityIntent)
        }
    }
}
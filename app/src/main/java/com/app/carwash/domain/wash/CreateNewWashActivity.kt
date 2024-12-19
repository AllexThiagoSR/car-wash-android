package com.app.carwash.domain.wash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.app.carwash.MainActivity
import com.app.carwash.R
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class CreateNewWashActivity : AppCompatActivity(), OnClickListener {
    private val backToMainButton: Button by lazy { findViewById(R.id.back_to_main_button) }
    private val washDateInput: TextInputEditText by lazy { findViewById(R.id.wash_date_input) }
    private val addButton: Button by lazy { findViewById(R.id.add_new_wash_button_form) }
    private val clientNameInput: TextInputEditText by lazy { findViewById(R.id.client_name_input) }
    private val vehicleModelInput: TextInputEditText by lazy { findViewById(R.id.vehicle_input) }
    private val valueInput: TextInputEditText by lazy { findViewById(R.id.value_input) }
    private val descriptionInput: TextInputEditText by lazy { findViewById(R.id.description_input) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_new_wash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        addNewWash(
//            "Allex",
//            "Mustang",
//            "2024-12-11",
//            120.0,
//            "Carro que foi lavado",
//        )
        backToMainButton.setOnClickListener(this)
        addButton.setOnClickListener(this)
        addButton.isEnabled = false
        clientNameInput.addTextChangedListener { verifyInputsToEnableButton() }
        vehicleModelInput.addTextChangedListener { verifyInputsToEnableButton() }
        washDateInput.addTextChangedListener { verifyInputsToEnableButton() }
        valueInput.addTextChangedListener { verifyInputsToEnableButton() }
        descriptionInput.addTextChangedListener { verifyInputsToEnableButton() }

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        washDateInput.setText("" + day + "/" + (month + 1) + "/" + year)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.back_to_main_button -> backToMainPage()
            R.id.add_new_wash_button_form -> onClickAddNewWash()
        }
    }

    private fun backToMainPage() {
        val mainActivityIntent = Intent(baseContext, MainActivity::class.java)
        startActivity(mainActivityIntent)
    }

    private fun onClickAddNewWash() {
        val splittedDate = washDateInput.text.toString().split('/')
        val clientName = clientNameInput.text.toString()
        val vehicleModel = vehicleModelInput.text.toString()
        val value = valueInput.text.toString().toDouble()
        val description = descriptionInput.text.toString()
        addNewWash(
            clientName=clientName,
            vehicleModel=vehicleModel,
            washDate="${splittedDate[2]}-${splittedDate[1]}-${splittedDate[0]}",
            value=value,
            description=description
        )
        backToMainPage()
    }

    private fun verifyInputsToEnableButton() {
        val isEmpty = clientNameInput.text.isNullOrEmpty()
                || vehicleModelInput.text.isNullOrEmpty()
                || washDateInput.text.isNullOrEmpty()
                || valueInput.text.isNullOrEmpty()
                || descriptionInput.text.isNullOrEmpty()
        if (
            isEmpty
            || valueInput.text.toString().toDouble() < 20.0
        ) {
            addButton.isEnabled = false
            return
        }
        addButton.isEnabled = true
    }
}
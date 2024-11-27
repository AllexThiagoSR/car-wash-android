package com.app.carwash.domain.wash

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.carwash.R

class WashActivity : AppCompatActivity() {
    private val clientNameTextView: TextView by lazy { findViewById(R.id.client_name_value_wash) }
    private val vehicleTextView: TextView by lazy { findViewById(R.id.vehicle_value_wash) }
    private val paidTextView: TextView by lazy { findViewById(R.id.paid_or_not_wash) }
    private val valueTextView: TextView by lazy { findViewById(R.id.value_value_wash) }
    private val dateTextView: TextView by lazy { findViewById(R.id.date_value_wash) }
    private val paymentMethodTextView: TextView by lazy { findViewById(R.id.payment_method_wash) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val wash = washes.find { it.id == intent.getStringExtra("washId") }
        if (wash == null) return
        clientNameTextView.text = wash.clientName
        vehicleTextView.text = wash.vehicleModel
        paidTextView.text = if (wash.paid) "Pago" else "Não Pago"
        var valueString = wash.value.toString()
        val splittedValue = valueString.split(".")
        if (splittedValue[1].length == 1) {
            valueString += "0"
        }
        valueTextView.text = valueString.replace(".", ",")
        dateTextView.text = wash.washDate.toString()
        if (wash.payment != null) {
            val text = "Método de pagamento: ${wash.payment.name}"
            paymentMethodTextView.text = text
        }
    }
}
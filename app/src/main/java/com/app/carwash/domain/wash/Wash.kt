package com.app.carwash.domain.wash

import com.app.carwash.domain.paymentmethod.PaymentMethod
import com.app.carwash.domain.paymentmethod.paymentMethods
import java.time.LocalDate

data class Wash(
    val id: String,
    val clientName: String,
    val vehicleModel: String,
    val washDate: String,
    val value: Double,
    val description: String,
    val paid: Boolean = false,
    val paymentTypeId: String? = null,
    val payment: PaymentMethod? = null
)

val washes: MutableList<Wash> = mutableListOf(
    Wash(
        "1",
        "Allek",
        "Palio",
        "2024-11-21",
        100.0,
        "Carro lavado com água e sabão",
    ),
    Wash(
        "2",
        "Thiago",
        "Fusca",
        ("2024-11-21"),
        80.0,
        "Carro lavado com água e sabão",
        true,
        "2d6685eb-ded8-43f9-a899-3ccc206a61da",
        paymentMethods.find { paymentMethod ->
            paymentMethod.id === "2d6685eb-ded8-43f9-a899-3ccc206a61da"
        }
    ),
    Wash(
        "3",
        "João",
        "Saveiro",
        ("2024-11-21"),
        60.0,
        "Carro lavado com água e sabão",
    )
)

fun addNewWash(clientName: String, vehicleModel: String, washDate: String, value: Double, description: String, paymentTypeId: String? = null) {
    val newWash = Wash(
        "${washes.size + 1}",
        clientName,
        vehicleModel,
        washDate,
        value,
        description,
        false,
        paymentTypeId,
        paymentMethods.find { paymentMethod ->
            paymentMethod.id === paymentTypeId
        }
    )
    washes.add(newWash)
}

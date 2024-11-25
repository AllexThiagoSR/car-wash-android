package com.app.carwash.domain.wash

import com.app.carwash.domain.paymentmethod.PaymentMethod
import com.app.carwash.domain.paymentmethod.paymentMethods
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Wash(
    val id: String,
    val clientName: String,
    val vehicleModel: String,
    val washDate: LocalDate,
    val value: Double,
    val description: String,
    val paid: Boolean = false,
    val paymentTypeId: String? = null,
    val payment: PaymentMethod? = null
)

val washes: List<Wash> = listOf(
    Wash(
        "1",
        "Allek",
        "Palio",
        LocalDate.parse("2024-11-21"),
        100.0,
        "Carro lavado com água e sabão",
    ),
    Wash(
        "1",
        "Thiago",
        "Fusca",
        LocalDate.parse("2024-11-21"),
        80.0,
        "Carro lavado com água e sabão",
        true,
        "2d6685eb-ded8-43f9-a899-3ccc206a61da",
        paymentMethods.find { paymentMethod ->
            paymentMethod.id === "2d6685eb-ded8-43f9-a899-3ccc206a61da"
        }
    ),
    Wash(
        "1",
        "João",
        "Saveiro",
        LocalDate.parse("2024-11-21"),
        60.0,
        "Carro lavado com água e sabão",
    )
)

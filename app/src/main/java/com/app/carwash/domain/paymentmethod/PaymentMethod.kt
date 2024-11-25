package com.app.carwash.domain.paymentmethod

data class PaymentMethod(
    val id: String,
    val name: String,
    val description: String
)

val paymentMethods: List<PaymentMethod> = listOf(
    PaymentMethod(
        "2d6685eb-ded8-43f9-a899-3ccc206a61da",
        "Pix",
        "Pagamento instantâneo via Pix, disponível 24 horas por dia."
    ),
    PaymentMethod(
        "10a909d4-ae84-4c2f-a025-a503b5b29b8e",
        "Transferência Bancária",
        "Pagamento via transferência entre contas bancárias."
    ),
    PaymentMethod(
        "379a3611-32f9-432d-9692-41ac690446a9",
        "Dinheiro",
        "Pagamento em mãos à vista."
    )
)
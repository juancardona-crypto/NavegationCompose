package com.example.navegationcompose.ui.data

import com.example.navegationcompose.R


data class Book(
    val id: String,
    val nombre: String,
    val autor: String,
    val fechaReserva: String,
    val codigoReserva: String,
    val estado: String,
    val imagen: Int? = null
)

object DataBooks{
    val books = listOf(
        Book(
            id = "1",
            nombre = "Clean Code",
            autor = "Robert C. Martin",
            fechaReserva = "15-06-2026",
            codigoReserva = "RES-2026-001",
            estado = "Activa",
            imagen = R.mipmap.ic_clean_code_foreground
        )
    )
}

package com.example.navegationcompose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.navegationcompose.ui.data.Book
import com.example.navegationcompose.ui.data.DataBooks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReservasViewModel : ViewModel() {

    var reservas by mutableStateOf(DataBooks.books)
        private set

    fun buscarPorNombre(nombre: String?): Book? =
        reservas.find { it.nombre == nombre }

    fun cancelarReserva(nombre: String?) {
        reservas = reservas.map { book ->
            if (book.nombre == nombre) book.copy(estado = "Cancelada") else book
        }
    }

    fun renovarReserva(nombre: String?, nuevaFecha: String) {
        reservas = reservas.map { book ->
            if (book.nombre == nombre) book.copy(fechaReserva = nuevaFecha, estado = "Activa") else book
        }
    }
}

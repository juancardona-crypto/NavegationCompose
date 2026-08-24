package com.example.navegationcompose.ui.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navegationcompose.ui.viewmodel.ReservasViewModel
import com.example.navegationcompose.ui.data.Book
import com.example.navegationcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisReservasScreen(navController: NavHostController, viewModel: ReservasViewModel) {
    val reservas = viewModel.reservas

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Reservas", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.List, contentDescription = "Menu")
                    }
                },
                actions = {
                    Icon(
                        Icons.Default.Book,
                        contentDescription = "Books",
                        modifier = Modifier.padding(end = 16.dp),
                        tint = Color(0xFF6750A4)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.List, contentDescription = null, tint = Color.Red, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Lista de reservas activas", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(reservas) { book ->
                    ReservaItem(book) {
                        val nombreEnc = Uri.encode(book.nombre)
                        val fechaEnc = Uri.encode(book.fechaReserva)
                        val codigoEnc = Uri.encode(book.codigoReserva)
                        navController.navigate("detalleReserva/$nombreEnc/$fechaEnc/$codigoEnc")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEADDFF)),
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = androidx.compose.foundation.shape.CircleShape,
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("i", color = Color(0xFF6750A4), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Aquí verás todas tus reservas activas.",
                        fontSize = 12.sp,
                        color = Color(0xFF6750A4)
                    )
                }
            }
        }
    }
}

@Composable
fun ReservaItem(book: Book, onVerReserva: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            book.imagen?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = book.nombre,
                    modifier = Modifier.size(90.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Libro:", fontSize = 12.sp, color = Color.Gray)
                Text(book.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text("Fecha:", fontSize = 12.sp, color = Color.Gray)
                Text(book.fechaReserva, fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text("Estado:", fontSize = 12.sp, color = Color.Gray)
                Surface(
                    color = if (book.estado == "Activa") Color(0xFFE8F5E9) else Color(0xFFFBE9E7),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = book.estado,
                        color = if (book.estado == "Activa") Color(0xFF2E7D32) else Color(0xFFD32F2F),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Button(
                    onClick = onVerReserva,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Icon(Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ver Reserva", fontSize = 14.sp)
                }
            }
        }
    }
}

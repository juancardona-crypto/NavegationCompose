package com.example.navegationcompose.ui.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navegationcompose.ui.viewmodel.ReservasViewModel
import com.example.navegationcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenovarReservaScreen(
    navController: NavHostController,
    viewModel: ReservasViewModel,
    nombreLibro: String?,
    fechaReserva: String?
) {
    var nuevaFecha by remember { mutableStateOf("30/06/2026") }
    val libro = viewModel.buscarPorNombre(nombreLibro)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Renovar Reserva", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    libro?.imagen?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Libro:", fontSize = 12.sp, color = Color.Gray)
                        Text(nombreLibro ?: "Desconocido", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text("Reserva Actual:", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = fechaReserva ?: "-",
                onValueChange = {},
                readOnly = true,
                leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledBorderColor = Color.LightGray,
                    disabledTextColor = Color.Black
                ),
                enabled = false
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Nueva Fecha:", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = nuevaFecha,
                onValueChange = { nuevaFecha = it },
                leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.renovarReserva(nombreLibro, nuevaFecha)
                    val nombreEnc = Uri.encode(nombreLibro)
                    val nuevaFechaEnc = Uri.encode(nuevaFecha)
                    navController.navigate("renovacion/$nombreEnc/$nuevaFechaEnc")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00)),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Confirmar Renovación", fontWeight = FontWeight.Bold)
            }
        }
    }
}

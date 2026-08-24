package com.example.navegationcompose.ui.navHost

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navegationcompose.ui.screen.DetalleReservaScreen
import com.example.navegationcompose.ui.screen.MisReservasScreen
import com.example.navegationcompose.ui.screen.RenovacionScreen
import com.example.navegationcompose.ui.screen.RenovarReservaScreen
import com.example.navegationcompose.ui.viewmodel.ReservasViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: ReservasViewModel = viewModel()

    NavHost(navController = navController, startDestination = "misReservas") {

        composable("misReservas") {
            MisReservasScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = "detalleReserva/{nombreLibro}/{fechaReserva}/{codigoReserva}",
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("fechaReserva") { type = NavType.StringType },
                navArgument("codigoReserva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombreLibro = backStackEntry.arguments?.getString("nombreLibro")
            val fechaReserva = backStackEntry.arguments?.getString("fechaReserva")
            val codigoReserva = backStackEntry.arguments?.getString("codigoReserva")
            DetalleReservaScreen(
                navController = navController,
                viewModel = viewModel,
                nombreLibro = nombreLibro,
                fechaReserva = fechaReserva,
                codigoReserva = codigoReserva
            )
        }

        composable(
            route = "renovarReserva/{nombreLibro}/{fechaReserva}",
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("fechaReserva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombreLibro = backStackEntry.arguments?.getString("nombreLibro")
            val fechaReserva = backStackEntry.arguments?.getString("fechaReserva")
            RenovarReservaScreen(
                navController = navController,
                viewModel = viewModel,
                nombreLibro = nombreLibro,
                fechaReserva = fechaReserva
            )
        }

        composable(
            route = "renovacion/{nombreLibro}/{fechaNueva}",
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("fechaNueva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombreLibro = backStackEntry.arguments?.getString("nombreLibro")
            val fechaNueva = backStackEntry.arguments?.getString("fechaNueva")
            RenovacionScreen(
                navController = navController,
                nombreLibro = nombreLibro,
                fechaNueva = fechaNueva
            )
        }
    }
}

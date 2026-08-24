package com.example.navegationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.navegationcompose.ui.navHost.Navigation
import com.example.navegationcompose.ui.theme.NavegationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegationComposeTheme(darkTheme = false) {
                Navigation()
            }
        }
    }
}

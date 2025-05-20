package com.example.a25_04_sdv_nantes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.a25_04_sdv_nantes.ui.screens.SearchScreen
import com.example.a25_04_sdv_nantes.ui.theme._25_04_sdv_nantesTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _25_04_sdv_nantesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SearchScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

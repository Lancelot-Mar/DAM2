package com.example.holajp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.holajp.ui.theme.HolaJPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DosTextosVertical()
        }
    }
}

@Composable
fun DosTextosVertical() {
    Row(modifier = Modifier.padding(top = 25.dp)) {
        Text("Tercer texto")
        Text("Cuarto texto")
    }
    Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
        Text("Primer texto")
        Text("Segundo texto")
    }
}

@Preview
@Composable
fun DosTextosVerticalPreview(){
    DosTextosVertical()
}
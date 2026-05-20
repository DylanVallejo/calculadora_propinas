package com.example.propinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.propinas.ui.theme.PropinasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PropinasTheme {
                CalculadoraPropinas()
            }
        }
    }
}

@Composable
fun CalculadoraPropinas() {
    val monto = remember { mutableStateOf("") }
    val porcentaje = remember { mutableStateOf("") }
    val resultado = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calculadora de Propinas", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = monto.value,
            onValueChange = { monto.value = it },
            label = { Text("Monto de la cuenta") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = porcentaje.value,
            onValueChange = { porcentaje.value = it },
            label = { Text("Porcentaje de propina (%)") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val montoNum = monto.value.toDoubleOrNull() ?: 0.0
            val porcentajeNum = porcentaje.value.toDoubleOrNull() ?: 0.0
            val propina = montoNum * (porcentajeNum / 100)
            resultado.value = "Propina: $%.2f".format(propina)
        }) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = resultado.value, fontSize = 20.sp)
    }
}

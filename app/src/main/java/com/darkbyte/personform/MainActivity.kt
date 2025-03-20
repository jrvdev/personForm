package com.darkbyte.personform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    private val db by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonaFormulario()
        }
    }

    @Composable
    fun PersonaFormulario() {
        var nombre by remember { mutableStateOf("") }
        var edad by remember { mutableStateOf("") }
        var telefono by remember { mutableStateOf("") }
        var ciudad by remember { mutableStateOf("") }
        val personas = remember { mutableStateListOf<Persona>() }
        val scope = rememberCoroutineScope()

        // Cargar personas al iniciar la app
        LaunchedEffect(Unit) {
            scope.launch {
                personas.clear()
                personas.addAll(db.personaDao().getAll())
            }
        }

        // Guardar Persona
        fun guardarPersona() {
            val persona = Persona(
                nombre = nombre,
                edad = edad.toIntOrNull() ?: 0,
                telefono = telefono,
                ciudad = ciudad
            )

            scope.launch {
                db.personaDao().insertar(persona)
                personas.clear()
                personas.addAll(db.personaDao().getAll())
                nombre = ""
                edad = ""
                telefono = ""
                ciudad = ""
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de Personas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = ciudad,
                onValueChange = { ciudad = it },
                label = { Text("Ciudad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { guardarPersona() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Personas Registradas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = personas) { persona ->
                    Text(
                        text = "${persona.nombre} - ${persona.edad} años - ${persona.telefono} - ${persona.ciudad}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

    }
    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        PersonaFormulario()
    }
}

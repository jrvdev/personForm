package com.darkbyte.personform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkbyte.personform.db.entity.Persona

/**
 * Composable que representa el formulario para registrar personas.
 *
 * Recibe un PersonaViewModel para interactuar con la base de datos y actualizar la UI.
 */
@Composable
fun PersonaFormulario(personaViewModel: PersonaViewModel? = null, previewPersonas: List<Persona> = emptyList()) {
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var editingPersona by remember { mutableStateOf<Persona?>(null) }

    var showMessage by remember { mutableStateOf("") }
    val personas = personaViewModel?.personas?.collectAsState()?.value ?: previewPersonas

    fun guardarOActualizarPersona() {
        if (personaViewModel != null) {
            if (editingPersona == null) {
                // Guardar nueva persona
                val persona = Persona(
                    nombre = nombre,
                    edad = edad.toIntOrNull() ?: 0,
                    telefono = telefono,
                    ciudad = ciudad
                )
                personaViewModel.guardarPersona(persona)
                showMessage = "Persona guardada correctamente."
            } else {
                // Actualizar persona existente
                val personaEditada = editingPersona!!.copy(
                    nombre = nombre,
                    edad = edad.toIntOrNull() ?: 0,
                    telefono = telefono,
                    ciudad = ciudad
                )
                personaViewModel.editarPersona(personaEditada)
                showMessage = "Persona actualizada correctamente."
                editingPersona = null
            }
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
            text = if (editingPersona == null) "Registro de Personas" else "Editar Persona",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))


        TextField(value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Telefono") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))


        TextField(value = ciudad,
            onValueChange = { ciudad = it },
            label = { Text("Ciudad") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { guardarOActualizarPersona() },
            modifier = Modifier.fillMaxWidth()) {
            Text(if (editingPersona == null) "Guardar" else "Actualizar")
        }

        if (showMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(showMessage)
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(modifier = Modifier
            .fillMaxWidth()) {
            items(personas) { persona ->
                PersonaItem(
                    persona = persona,
                    onDelete = { personaViewModel?.eliminarPersona(persona) },
                    onEdit = {
                        editingPersona = persona
                        nombre = persona.nombre
                        edad = persona.edad.toString()
                        telefono = persona.telefono
                        ciudad = persona.ciudad
                    }
                )
            }
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPersonaFormulario() {
    // Datos ficticios para el Preview
    val examplePersonas = listOf(
        Persona(1, "Juan", 30, "123456789", "Madrid"),
        Persona(2, "Ana", 25, "987654321", "Barcelona"),
        Persona(3, "Pedro", 40, "555555555", "Sevilla")
    )

    PersonaFormulario(
        personaViewModel = null,
        previewPersonas = examplePersonas
    )
}


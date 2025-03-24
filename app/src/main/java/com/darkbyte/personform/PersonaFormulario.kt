package com.darkbyte.personform

import androidx.compose.foundation.layout.*
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
 * Composable que representa el formulario para registrar y editar personas.
 * Se conecta con el ViewModel para guardar, editar y eliminar personas en la base de datos.
 * También soporta un modo de previsualización con datos ficticios.
 *
 * @param personaViewModel ViewModel que gestiona las operaciones con la base de datos.
 * @param previewPersonas Lista de personas ficticias utilizadas para la vista previa en
 * caso de que el ViewModel sea nulo.
 */
@Composable
fun PersonaFormulario(
    personaViewModel: PersonaViewModel? = null,
    previewPersonas: List<Persona> = emptyList()) {

    // Estado de los campos de texto del formulario
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var editingPersona by remember { mutableStateOf<Persona?>(null) }

    // Mensaje de confirmación de la accion
    var showMessage by remember { mutableStateOf("") }

    // Obtencion de la lista de personas desde el ViewModel o desde datos ficticios
    // (para previsualización)
    val personas = personaViewModel?.personas?.collectAsState()?.value ?: previewPersonas

    /**
     * Funcion que maneja la lógica de guardar o actualizar una persona
     * dependiendo de si se esta editando.
     */
    fun guardarOActualizarPersona() {
        if (personaViewModel != null) {
            if (editingPersona == null) {
                // Crear una nueva persona y guardarla
                val persona = Persona(
                    nombre = nombre,
                    edad = edad.toIntOrNull() ?: 0,
                    telefono = telefono,
                    ciudad = ciudad
                )
                personaViewModel.guardarPersona(persona)
                showMessage = "Persona guardada correctamente."
            } else {
                // Actualizar una persona existente
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
            // Restablecer los campos del formulario
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
        // Título principal del formulario
        Text(
            text = if (editingPersona == null) "Registro de Personas"
            else "Editar Persona",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de entrada para los datos de la persona
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
            label = { Text("Teléfono") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = ciudad,
            onValueChange = { ciudad = it },
            label = { Text("Ciudad") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar o actualizar la persona
        Button(onClick = { guardarOActualizarPersona() },
            modifier = Modifier.fillMaxWidth()) {
            Text(if (editingPersona == null) "Guardar" else "Actualizar")
        }

        // Mostrar mensaje de confirmación
        if (showMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(showMessage)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Lista de personas
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(personas) { persona ->
                PersonaItem(
                    persona = persona,
                    onDelete = { personaViewModel?.eliminarPersona(persona) },
                    onEdit = {
                        // Al hacer clic en editar, se cargan los datos de la persona para editar
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

/**
 * Vista previa de la pantalla de formulario con datos ficticios.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPersonaFormulario() {
    // Datos ficticios para el Preview
    val examplePersonas = listOf(
        Persona(1, "Juan", 30, "123456789", "Madrid"),
        Persona(2, "Ana", 25, "987654321", "Barcelona"),
        Persona(3, "Pedro", 40, "555555555", "Sevilla")
    )

    // Llamada al formulario con personas ficticias
    PersonaFormulario(
        personaViewModel = null,  // En la vista previa no se necesita el ViewModel
        previewPersonas = examplePersonas
    )
}

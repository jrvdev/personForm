package com.darkbyte.personform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.darkbyte.personform.db.entity.Persona

/**
 * Composable que muestra un item de una persona dentro de una tarjeta.
 * Este componente recibe los datos de una persona y acciones para editar y eliminar.
 *
 * @param persona Objeto que contiene los datos de la persona (nombre, edad, telefono, ciudad).
 * @param onDelete Función que se ejecuta cuando el usuario desea eliminar a la persona.
 * @param onEdit Función que se ejecuta cuando el usuario desea editar los datos de la persona.
 */
@Composable
fun PersonaItem(persona: Persona, onDelete: () -> Unit, onEdit: () -> Unit) {
    // Tarjeta que contiene toda la información de la persona.
    Card(
        modifier = Modifier
            .fillMaxWidth() // La tarjeta ocupara el ancho completo disponible
            .padding(8.dp), // Margen alrededor de la tarjeta
        elevation = CardDefaults.cardElevation(4.dp) // Sombra para darle elevación a la tarjeta
    ) {
        // Contenedor con un layout de fila para mostrar los datos y los botones de acción.
        Row(
            modifier = Modifier
                .fillMaxWidth() // La fila ocupara el ancho disponible
                .padding(16.dp), // Margen alrededor de la fila
            horizontalArrangement = Arrangement.SpaceBetween, // Espacio entre los elementos
            verticalAlignment = Alignment.CenterVertically // Alineación vertical centrada
        ) {
            // Columna que contiene los datos de la persona.
            Column {
                // Nombre de la persona.
                Text(text = "Nombre: ${persona.nombre}", fontWeight = FontWeight.Bold)
                // Edad de la persona.
                Text(text = "Edad: ${persona.edad}", fontWeight = FontWeight.Bold)
                // Teléfono de la persona.
                Text(text = "Teléfono: ${persona.telefono}", fontWeight = FontWeight.Bold)
                // Ciudad de la persona.
                Text(text = "Ciudad: ${persona.ciudad}", fontWeight = FontWeight.Bold)
            }
            // Fila para los botones de editar y eliminar.
            Row {
                // Botón para editar, ejecuta la accion 'onEdit' cuando es presionado.
                IconButton(onClick = { onEdit() }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                // Botón para eliminar, ejecuta la accion 'onDelete' cuando es presionado.
                IconButton(onClick = { onDelete() }) {
                    // El icono de eliminar tendra un color de error (rojo).
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}



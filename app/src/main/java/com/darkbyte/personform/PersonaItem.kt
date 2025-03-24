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

@Composable
fun PersonaItem(persona: Persona, onDelete: () -> Unit, onEdit: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Nombre: ${persona.nombre}", fontWeight = FontWeight.Bold)
                Text(text = "Edad: ${persona.edad}", fontWeight = FontWeight.Bold)
                Text(text = "Teléfono: ${persona.telefono}", fontWeight = FontWeight.Bold)
                Text(text = "Ciudad: ${persona.ciudad}", fontWeight = FontWeight.Bold)
            }
            Row {
                IconButton(onClick = { onEdit() }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = { onDelete() }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}


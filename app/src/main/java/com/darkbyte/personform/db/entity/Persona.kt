package com.darkbyte.personform.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa una Persona en la base de datos.
 *
 * Cada propiedad se mapea a una columna en la tabla "personas".
 */
@Entity(tableName = "personas")
data class Persona(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val edad: Int,
    val telefono: String,
    val ciudad: String
)
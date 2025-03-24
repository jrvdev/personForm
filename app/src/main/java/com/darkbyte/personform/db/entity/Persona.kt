package com.darkbyte.personform.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa una Persona en la base de datos.
 * Cada propiedad de la clase se mapea a una columna en la tabla "personas" de la base de datos.
 */
@Entity(tableName = "personas")
data class Persona(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Clave primaria autogenerada
    val nombre: String,  // Nombre de la persona
    val edad: Int,       // Edad de la persona
    val telefono: String,  // Telefono de la persona
    val ciudad: String    // Ciudad de la persona
)

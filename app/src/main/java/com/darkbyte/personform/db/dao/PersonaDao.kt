package com.darkbyte.personform.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.darkbyte.personform.db.entity.Persona

/**
 * Interfaz DAO que define las operaciones de acceso a datos para la entidad Persona.
 */
@Dao
interface PersonaDao {

    @Insert
    suspend fun insertar(persona: Persona)

    @Query("SELECT * FROM personas")
    suspend fun getAll(): List<Persona>

    @Delete
    suspend fun eliminar(persona: Persona)

    @Update
    suspend fun actualizar(persona: Persona)
}


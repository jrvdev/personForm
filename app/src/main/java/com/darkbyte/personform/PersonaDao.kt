package com.darkbyte.personform

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonaDao {

    @Insert
    suspend fun insertar(persona: Persona)

    @Query("SELECT * FROM personas")
    suspend fun getAll(): List<Persona>
}


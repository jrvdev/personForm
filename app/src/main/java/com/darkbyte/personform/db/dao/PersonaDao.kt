package com.darkbyte.personform.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.darkbyte.personform.db.entity.Persona

/**
 * Interfaz DAO (Data Access Object) que define las operaciones de acceso a datos para la entidad `Persona`.
 * Esta interfaz contiene metodos para insertar, consultar, eliminar y actualizar registros de personas
 * en la base de datos utilizando Room.
 */
@Dao
interface PersonaDao {


    //Inserta una nueva persona en la base de datos.
    @Insert
    suspend fun insertar(persona: Persona)

    /**
     Obtiene todas las personas almacenadas en la base de datos.
     @return Una lista de objetos `Persona` que representa todas las personas en la base de datos.
     */
    @Query("SELECT * FROM personas")
    suspend fun getAll(): List<Persona>

    //Elimina una persona de la base de datos.
    @Delete
    suspend fun eliminar(persona: Persona)

    //Actualiza la información de una persona en la base de datos.
    @Update
    suspend fun actualizar(persona: Persona)
}

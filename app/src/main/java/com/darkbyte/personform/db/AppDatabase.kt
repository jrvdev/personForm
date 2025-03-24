package com.darkbyte.personform.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.darkbyte.personform.db.entity.Persona
import com.darkbyte.personform.db.dao.PersonaDao

/**
 * Clase que representa la base de datos Room.
 *
 * Contiene la definición de la entidad Persona y expone el DAO correspondiente.
 */
@Database(entities = [Persona::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provee el acceso a las operaciones definidas en el DAO de Persona.
     */
    abstract fun personaDao(): PersonaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Método para obtener la instancia única de la base de datos.
         *
         * Se utiliza el patrón Singleton para asegurarnos de que solo haya una instancia.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personas_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
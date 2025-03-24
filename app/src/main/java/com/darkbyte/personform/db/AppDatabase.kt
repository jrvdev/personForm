package com.darkbyte.personform.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.darkbyte.personform.db.entity.Persona
import com.darkbyte.personform.db.dao.PersonaDao

/**
 * Clase que representa la base de datos Room.
 * Define la base de datos de la aplicación, incluyendo la entidad `Persona` y el DAO correspondiente.
 * Utiliza Room para gestionar la creación, actualización y acceso a la base de datos.
 */
@Database(entities = [Persona::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provee el acceso a las operaciones definidas en el DAO de `Persona`.
     * @return Una instancia del DAO para interactuar con la entidad `Persona`.
     */
    abstract fun personaDao(): PersonaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Metodo para obtener la instancia unica de la base de datos.
         * Utiliza el patrón Singleton para asegurarse de que solo haya una instancia de la base de datos
         * en toda la aplicación. La base de datos es construida solo si no existe una instancia previa.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Construir la base de datos si no existe una instancia previa
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personas_database"  // Nombre del archivo de la base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

package com.darkbyte.personform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.darkbyte.personform.db.AppDatabase
import com.darkbyte.personform.db.entity.Persona
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar la logica de la base de datos relacionada con las personas.
 * Proporciona funciones para cargar, guardar, eliminar y editar personas.
 *
 * @param application Aplicacion utilizada para obtener acceso a la base de datos.
 */
class PersonaViewModel(application: Application) : AndroidViewModel(application) {

    // Instancia de la base de datos
    private val db = AppDatabase.getDatabase(application)

    // Estado mutable que almacena la lista de personas
    private val _personas = MutableStateFlow<List<Persona>>(emptyList())

    // Estado inmutable para exponer la lista de personas
    val personas: StateFlow<List<Persona>> = _personas.asStateFlow()

    // Inicializa el ViewModel y carga las personas desde la base de datos
    init {
        cargarPersonas()
    }

    /**
     * Carga la lista de personas desde la base de datos de manera asíncrona.
     */
    private fun cargarPersonas() {
        viewModelScope.launch {
            // Actualiza la lista de personas en el estado
            _personas.value = db.personaDao().getAll()
        }
    }

    /**
     * Guarda una nueva persona en la base de datos.
     * Después de guardar, actualiza la lista de personas.
     *
     * @param persona Objeto que contiene los datos de la persona a guardar.
     */
    fun guardarPersona(persona: Persona) {
        viewModelScope.launch {
            // Inserta la nueva persona en la base de datos
            db.personaDao().insertar(persona)
            // Actualiza la lista de personas después de insertar
            cargarPersonas()
        }
    }

    /**
     * Elimina una persona de la base de datos.
     * Despues de eliminar, actualiza la lista de personas.
     *
     * @param persona Objeto que representa la persona a eliminar.
     */
    fun eliminarPersona(persona: Persona) {
        viewModelScope.launch {
            // Elimina la persona de la base de datos
            db.personaDao().eliminar(persona)
            // Actualiza la lista de personas después de eliminar
            cargarPersonas()
        }
    }

    /**
     * Edita los datos de una persona en la base de datos.
     * Después de actualizar, refresca la lista de personas.
     *
     * @param persona Objeto con los datos actualizados de la persona.
     */
    fun editarPersona(persona: Persona) {
        viewModelScope.launch {
            // Actualiza los datos de la persona en la base de datos
            db.personaDao().actualizar(persona)
            // Refresca la lista de personas con los datos actualizados
            cargarPersonas()
        }
    }
}


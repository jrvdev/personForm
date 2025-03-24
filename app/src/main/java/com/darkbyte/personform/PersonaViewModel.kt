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

class PersonaViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val _personas = MutableStateFlow<List<Persona>>(emptyList())
    val personas: StateFlow<List<Persona>> = _personas.asStateFlow()

    init {
        cargarPersonas()
    }

    private fun cargarPersonas() {
        viewModelScope.launch {
            _personas.value = db.personaDao().getAll()
        }
    }

    fun guardarPersona(persona: Persona) {
        viewModelScope.launch {
            db.personaDao().insertar(persona)
            cargarPersonas()  // Actualizamos la lista después de insertar
        }
    }

    fun eliminarPersona(persona: Persona) {
        viewModelScope.launch {
            db.personaDao().eliminar(persona)
            cargarPersonas()  // Actualizamos la lista después de eliminar
        }
    }

    fun editarPersona(persona: Persona) {
        viewModelScope.launch {
            db.personaDao().actualizar(persona)
            cargarPersonas()  // Refrescar la lista con los cambios actualizados
        }
    }

}

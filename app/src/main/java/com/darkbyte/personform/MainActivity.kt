package com.darkbyte.personform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Crear automáticamente el ViewModel con acceso al contexto de la aplicación
            val personaViewModel: PersonaViewModel = viewModel()
            PersonaFormulario(personaViewModel = personaViewModel)
        }
    }
}

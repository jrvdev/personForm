package com.darkbyte.personform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Actividad principal de la aplicación que establece el contenido de la UI .
 * Crea y configura el ViewModel `PersonaViewModel`, que gestiona la logica y las operaciones
 * relacionadas con las personas. Luego, pasa ese ViewModel a la Composable `PersonaFormulario`
 * para que la UI pueda interactuar con la base de datos.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Establecer el contenido de la actividad con un Composable
        setContent {
            // Crear automaticamente el ViewModel con acceso al contexto de la aplicación
            val personaViewModel: PersonaViewModel = viewModel()

            // Llamar a PersonaFormulario pasandole el ViewModel
            PersonaFormulario(personaViewModel = personaViewModel)
        }
    }
}

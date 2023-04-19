package com.example.projectoroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectoroom.database.CiudadRoomDatabase
import com.example.projectoroom.database.RoomRepository
import com.example.projectoroom.navigation.AppNavHost
import com.example.projectoroom.ui.theme.ProjectoRoomTheme

class MainActivity : ComponentActivity() {
    // Obtenemos la base de datos
    val database by lazy { CiudadRoomDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializamos el repositorio de la base de datos
        RoomRepository.init(database.ciudadDao())

        setContent {
            ProjectoRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavHost()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectoRoomTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            AppNavHost()
        }
    }
}
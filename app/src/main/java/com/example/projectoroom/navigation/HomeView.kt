package com.example.projectoroom.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projectoroom.database.RoomRepository

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(navController: NavHostController) {
    // Recuperamos la instancia del repositorio
    // y obtenemos la lista de las ciudades
    val repository = RoomRepository.getInstance()
    val ciudades by repository.ciudades.collectAsState(listOf())

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar("Ciudades")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("insert")
            }) {
                Icon(Icons.Filled.Add, "Añadir estudiante")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        if (ciudades.isNotEmpty()) {
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(items = ciudades, key = { c -> c.id }) {
                    ListItem(
                        text = { Text(it.nombre) },
                        secondaryText = { Text("Población: ${it.poblacion}") }
                    )
                }
            }
        } else {
            Text("No hay ciudades")
        }
    }
}
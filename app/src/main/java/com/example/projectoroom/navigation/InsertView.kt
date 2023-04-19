package com.example.projectoroom.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectoroom.database.Ciudad
import com.example.projectoroom.database.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun InsertView(navController: NavController) {
    val repository = RoomRepository.getInstance()
    val coroutineScope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("") }
    var poblacion by remember { mutableStateOf("") }

    fun clearInput() {
        nombre = ""
        poblacion = ""
    }

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.imePadding(),
        scaffoldState = scaffoldState,
        topBar = {
            AppBar("Añadir ciudad", navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = {
                Text(text = "Nombre")
            })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = poblacion,
                onValueChange = { poblacion = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = {
                    Text(text = "Población")
                })
            Spacer(modifier = Modifier.height(32.dp))

            Row {
                OutlinedButton(onClick = {
                    clearInput()
                }) {
                    Text("Limpiar")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    val poblacionInt = poblacion.toInt()
                    val ciudad = Ciudad(0, nombre, poblacionInt)

                    coroutineScope.launch(Dispatchers.IO) {
                        try {
                            repository.insert(ciudad)
                            clearInput()
                            scaffoldState.snackbarHostState.showSnackbar(
                                "La ciudad fue añadida"
                            )
                        } catch (e: Exception) {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "La ciudad no pudo ser añadida",
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                }) {
                    Text("Añadir")
                }
            }
        }
    }
}
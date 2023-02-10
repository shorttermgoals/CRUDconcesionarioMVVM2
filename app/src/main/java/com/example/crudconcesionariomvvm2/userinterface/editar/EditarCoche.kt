package com.example.crudconcesionariomvvm2.userinterface.editar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crudconcesionariomvvm2.datos.db
import com.example.crudconcesionariomvvm2.datos.nombre_coleccion

@Composable
fun EditarCoche(ViewModelEditar:ViewModelEditar) {

    val db = db
    var nombre_coleccion = nombre_coleccion

    val matriculaBusqueda:String by ViewModelEditar.matriculaBusqueda.observeAsState (initial = "")
    val id:String by ViewModelEditar.id.observeAsState (initial = "")
    val nombre_coche:String by ViewModelEditar.nombre.observeAsState (initial = "")
    val modelo_coche:String by ViewModelEditar.modelo.observeAsState (initial = "")
    val km_coche:String by ViewModelEditar.km.observeAsState (initial = "")
    val anio_coche:String by ViewModelEditar.anio.observeAsState (initial = "")
    val isButtonEnable:Boolean by ViewModelEditar.isButtonEnable.observeAsState (initial = false)
    val confirmation_message:String by ViewModelEditar.confirmation_message.observeAsState(initial = "")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column( modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(   text = "Editar coche",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 15.dp))
                //Input de texto
                OutlinedTextField(
                    value = matriculaBusqueda,
                    onValueChange = { ViewModelEditar.onCompletedFields(matriculaBusqueda = it,id = id, nombre = nombre_coche, modelo = modelo_coche, km = km_coche, anio = anio_coche) },
                    label = { Text("Matrícula del coche a editar") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    singleLine = true,
                )
                //Input de texto
                OutlinedTextField(
                    value = id,
                    onValueChange = { ViewModelEditar.onCompletedFields(matriculaBusqueda = matriculaBusqueda,id = it, nombre = nombre_coche, modelo = modelo_coche, km = km_coche, anio = anio_coche)  },
                    label = { Text("Matrícula") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    singleLine = true,
                )
                //Input de texto
                OutlinedTextField(
                    value = nombre_coche,
                    onValueChange = { ViewModelEditar.onCompletedFields(matriculaBusqueda = matriculaBusqueda,id = id, nombre = it, modelo = modelo_coche, km = km_coche, anio = anio_coche)  },
                    label = { Text("Marca") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    singleLine = true,
                )
                //Input de texto
                OutlinedTextField(
                    value = modelo_coche,
                    onValueChange = { ViewModelEditar.onCompletedFields(matriculaBusqueda = matriculaBusqueda,id = id, nombre = nombre_coche, modelo = it, km = km_coche, anio = anio_coche)  },
                    label = { Text("Modelo") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    singleLine = true,
                )
                //Input de texto
                OutlinedTextField(
                    value = km_coche,
                    onValueChange = { ViewModelEditar.onCompletedFields(matriculaBusqueda = matriculaBusqueda,id = id, nombre = nombre_coche, modelo = modelo_coche, km = it, anio = anio_coche)  },
                    label = { Text("KM") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    singleLine = true,
                )
                //Input de texto
                OutlinedTextField(
                    value = anio_coche,
                    onValueChange = { ViewModelEditar.onCompletedFields(matriculaBusqueda = matriculaBusqueda,id = id, nombre = nombre_coche, modelo = modelo_coche, km = km_coche, anio = it)  },
                    label = { Text("Año") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    singleLine = true,
                )

                val dato = hashMapOf(
                    "matricula" to id.toString(),
                    "nombre" to nombre_coche.toString(),
                    "modelo" to modelo_coche.toString(),
                    "km" to km_coche.toString(),
                    "anio" to anio_coche.toString()
                )


                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    onClick = {
                        ViewModelEditar.editarButton(db,nombre_coleccion, matriculaBusqueda, dato)
                    }) {
                    Text(text = "Editar")
                }

                Text(text = confirmation_message, modifier = Modifier.padding(top = 10.dp))





            }
        }
    }
}
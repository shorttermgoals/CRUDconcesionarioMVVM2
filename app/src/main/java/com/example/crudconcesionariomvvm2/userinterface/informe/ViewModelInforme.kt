package com.example.crudconcesionariomvvm2.userinterface.informe

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelInforme:ViewModel() {

    private val _datos = MutableLiveData<String>()
    val datos : LiveData<String> = _datos

    fun informeButton(db: FirebaseFirestore, nombre_coleccion:String) {
        _datos.value = ""

        db.collection(nombre_coleccion)
            .get()


            .addOnSuccessListener { resultado ->
                for (encontrado in resultado) {

                    _datos.value += "Matrícula: ${encontrado.getString("id")}\n" //OJO nif o id
                    _datos.value += "Nombre: ${encontrado.getString("nombre")}\n"
                    _datos.value += "Modelo: ${encontrado.getString("modelo")}\n"
                    _datos.value += "KM: ${encontrado.getString("km")}\n"
                    _datos.value += "Año: ${encontrado.getString("anio")}\n\n"

                }

                if (_datos.toString().isEmpty()) {
                    _datos.value = "ERROR: No existen datos en la base de datos."
                }
            }
            .addOnFailureListener { resultado ->
                _datos.value = "ERROR: Ha habido un error. Por favor inténtelo de nuevo o más tarde."
            }
    }
}



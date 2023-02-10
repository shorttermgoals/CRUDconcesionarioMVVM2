package com.example.crudconcesionariomvvm2.userinterface.guardar

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelGuardar:ViewModel() {

    private val _id = MutableLiveData<String>()
    val id : LiveData<String> = _id

    private val _nombre = MutableLiveData<String>()
    val nombre : LiveData<String> = _nombre

    private val _modelo = MutableLiveData<String>()
    val modelo : LiveData<String> = _modelo

    private val _km = MutableLiveData<String>()
    val km : LiveData<String> = _km

    private val _anio = MutableLiveData<String>()
    val anio : LiveData<String> = _anio

    private val _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    private val _confirmation_message = MutableLiveData<String>()
    val confirmation_message: LiveData<String> = _confirmation_message

    fun onCompletedFields(id:String, nombre:String, modelo:String, km:String, anio:String) {
        _id.value = id
        _nombre.value = nombre
        _modelo.value = modelo
        _km.value = km
        _anio.value = anio
        _isButtonEnable.value = enableButton(id, nombre, modelo, km, anio)
        _confirmation_message.value = ""
    }

    fun enableButton(id:String, nombre:String, modelo:String, km:String, anio:String) =
        id.length>0 && nombre.length>0 && modelo.length>0 && km.length>0 && anio.length>0


    fun guardarButton(db: FirebaseFirestore, nombre_coleccion:String, id: String, dato: HashMap<String, String>) {
        db.collection(nombre_coleccion)
            .document(id)
            .set(dato)
            .addOnSuccessListener {
                buttonSuccess()
            }
            .addOnFailureListener {
                buttonFail()
            }
    }
    fun buttonSuccess(){
        _confirmation_message.value = "ÉXITO: Coche guardado de la base de datos."
        _id.value = ""
        _nombre.value = ""
        _modelo.value = ""
        _km.value = ""
        _anio.value = ""
    }
    fun buttonFail(){
        _confirmation_message.value = "ERROR: Ha habido un error. Por favor inténtelo de nuevo o más tarde."
    }
}



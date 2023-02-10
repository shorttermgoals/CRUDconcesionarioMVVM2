package com.example.crudconcesionariomvvm2.userinterface.editar

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelEditar:ViewModel() {

    private val _matriculaBusqueda = MutableLiveData<String>()
    val matriculaBusqueda : LiveData<String> = _matriculaBusqueda

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

    fun onCompletedFields(matriculaBusqueda:String, id:String, nombre:String, modelo:String, km:String, anio:String) {
        _matriculaBusqueda.value = matriculaBusqueda
        _id.value = id
        _nombre.value = nombre
        _modelo.value = modelo
        _km.value = km
        _anio.value = anio
        _isButtonEnable.value = enableButton(matriculaBusqueda, id, nombre, modelo, km, anio)
        _confirmation_message.value = ""
    }

    fun enableButton(matriculaBusqueda: String,id:String, nombre:String, modelo:String, km:String, anio:String) =
        matriculaBusqueda.length>0 && id.length>0 && nombre.length>0 && modelo.length>0 && km.length>0 && anio.length>0


    fun editarButton(db: FirebaseFirestore, nombre_coleccion:String, matriculaBusqueda: String, dato: HashMap<String, String>) {
        db.collection(nombre_coleccion)
            .document(matriculaBusqueda)
            .get()
            .addOnSuccessListener { documentSnapshot ->

                //COMPRUEBA QUE EL IDBUSCADO (ID A MODIFICAR) EXISTE EN LA BBDD
                if (documentSnapshot.exists()) {

                    //COMPRUEBA QUE EL NUEVO ID NO EXISTE YA EN LA BBDD PUES DEBE SER ÚNICO, SALVO QUE DICHO ID SEA IGUAL AL IDBUSCADO (NO HAY MODIFICACION DE ID)
                    if (documentSnapshot.data?.get("id") != id.toString() || matriculaBusqueda == id.toString()) {
                        db.collection(nombre_coleccion)
                            .document(matriculaBusqueda)
                            .set(dato)
                            .addOnSuccessListener {
                                buttonSuccess()
                            }
                            .addOnFailureListener {
                                buttonFail()
                            }
                    } else {
                        _confirmation_message.value = "ERROR: Nuevo ID introducido ya existe."
                    }
                } else {
                    _confirmation_message.value = "ERROR: El cliente buscado no existe en la base de datos."
                }
            }
            .addOnFailureListener {
                _confirmation_message.value = "ERROR: Ha habido un error. Por favor inténtelo de nuevo o más tarde."
        }
    }
    fun buttonSuccess(){
        _confirmation_message.value = "ÉXITO: Coche guardado de la base de datos."
        _matriculaBusqueda.value = ""
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



package com.example.crudconcesionariomvvm2.userinterface.menu

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class ViewModelMenu: ViewModel() {
    fun rutaButton(navController: NavHostController,ruta:String){
        navController.navigate(ruta)
    }
}
package com.example.crudconcesionariomvvm2.userinterface.navigation

sealed class AppScreens(val ruta:String) {
    object Menu: AppScreens("Menu")
    object GuardarCoche: AppScreens("GuardarCoche")
    object EditarCoche: AppScreens("EditarCoche")
    object EliminarCoche: AppScreens("EliminarCoche")
    object ConsultarCoche: AppScreens("ConsultarCoche")
    object InformeCoches: AppScreens("InformeCoches")
}
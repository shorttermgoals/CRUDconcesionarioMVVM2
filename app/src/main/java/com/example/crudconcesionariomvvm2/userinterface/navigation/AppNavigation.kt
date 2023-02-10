package com.example.crudconcesionariomvvm2.userinterface.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crudconcesionariomvvm2.userinterface.consultar.ConsultarCoche
import com.example.crudconcesionariomvvm2.userinterface.consultar.ViewModelConsultar
import com.example.crudconcesionariomvvm2.userinterface.editar.ViewModelEditar
import com.example.crudconcesionariomvvm2.userinterface.editar.EditarCoche
import com.example.crudconcesionariomvvm2.userinterface.eliminar.ViewModelEliminar
import com.example.crudconcesionariomvvm2.userinterface.eliminar.EliminarCoche
import com.example.crudconcesionariomvvm2.userinterface.guardar.ViewModelGuardar
import com.example.crudconcesionariomvvm2.userinterface.guardar.GuardarCoche
import com.example.crudconcesionariomvvm2.userinterface.informe.InformeCoches
import com.example.crudconcesionariomvvm2.userinterface.informe.ViewModelInforme
import com.example.crudconcesionariomvvm2.userinterface.menu.ViewModelMenu
import com.example.crudconcesionariomvvm2.userinterface.menu.Menu

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.Menu.ruta)

    {

        composable(AppScreens.Menu.ruta) { Menu(ViewModelMenu(), navigationController) }
        composable(AppScreens.GuardarCoche.ruta) { GuardarCoche(ViewModelGuardar()) }
        composable(AppScreens.EditarCoche.ruta) { EditarCoche(ViewModelEditar()) }
        composable(AppScreens.EliminarCoche.ruta) { EliminarCoche(ViewModelEliminar()) }
        composable(AppScreens.ConsultarCoche.ruta) { ConsultarCoche(ViewModelConsultar()) }
        composable(AppScreens.InformeCoches.ruta) { InformeCoches(ViewModelInforme()) }

    }
}
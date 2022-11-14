package com.example.melichallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.melichallenge.SplashScreen
import com.example.melichallenge.view.detailsitem.DetailsItemScreen
import com.example.melichallenge.view.main.MainScreen
import com.example.melichallenge.view.searchitems.SearchItemsScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.MainScreen.route){
            MainScreen(navController)
        }
        composable(AppScreens.SearchItemsScreen.route) {
            it.arguments?.getString("search")?.let { search ->
                SearchItemsScreen(search, navController)
            }
        }
        composable(AppScreens.DetailsItemsScreen.route){
            it.arguments?.getString("idProduct")?.let { idProduct ->
                DetailsItemScreen(idProduct, navController)
            }
        }
    }
}
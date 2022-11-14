package com.example.melichallenge.navigation

object Destinations {
    const val SEARCH_ITEMS_SCREEN = "SEARCH_ITEMS_SCREEN"
    const val DETAILS_ITEMS_SCREEN = "DETAILS_ITEMS_SCREEN"
}

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object MainScreen: AppScreens("main_screen")
    object SearchItemsScreen: AppScreens("${Destinations.SEARCH_ITEMS_SCREEN}/{search}")
    object DetailsItemsScreen: AppScreens("${Destinations.DETAILS_ITEMS_SCREEN}/{idProduct}")
}
package com.example.melichallenge

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.challengemeli.R
import com.example.melichallenge.navigation.AppNavigation
import com.example.melichallenge.ui.theme.MeliChallengeTheme
import com.example.melichallenge.view.detailsitem.DetailsItemScreen
import com.example.melichallenge.view.main.MainScreen
import com.example.melichallenge.view.searchitems.SearchItemsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        lateinit var mainContext: Context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainContext = this
        setContent {
            MeliChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = colorResource(R.color.background),
                ) {
                   AppNavigation()
                }
            }
        }
    }
}
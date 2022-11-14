package com.example.melichallenge.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.challengemeli.R
import com.example.melichallenge.navigation.AppScreens

@Composable
fun BottomBar(
    navController: NavController
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center)
    {
        OutlinedButton(onClick = {
            navController.popBackStack(
                route = AppScreens.MainScreen.route,
                inclusive = false,
                saveState = true)},
            modifier = Modifier
                .height(50.dp)
                .alpha(0.5f),
            shape = CircleShape,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor =  colorResource(R.color.yellow),
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home",
            )
        }
    }
}
package com.example.melichallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.challengemeli.R
import com.example.melichallenge.navigation.Destinations
import com.example.melichallenge.util.Utilities

/**
 * NoInternetView: composable creado para controlar
 * e informa fallo en la conexion de internet
 * @param navController
 * @param showButtonRetry: permite validar si se pinta en pantalla textBotton para reintentar
 * @param itemSearch: permite conocer el item a buscar, si se pinta el boton reintentar
 */
@Composable
fun NoInternetView(
    navController: NavController,
    showButtonRetry: Boolean? = false,
    itemSearch: String? = "") {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(R.color.gray)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Search",
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = stringResource(R.string.there_is_no_internet),
            textAlign = TextAlign.Center,
            fontSize = 20.sp)
        Text(
            text = stringResource(R.string.description_there_is_no_internet),
            textAlign = TextAlign.Center)

        if (showButtonRetry == true)
            TextButton(onClick = {
                if(Utilities().isConnected())
                    navController.navigate("${Destinations.SEARCH_ITEMS_SCREEN}/${itemSearch}")
            }) {
                Text(
                    text = stringResource(R.string.retry),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp)
            }
    }
}

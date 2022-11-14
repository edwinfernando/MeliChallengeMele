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
import com.example.melichallenge.util.Utilities

/**
 * NoApiResponseView: composable creado para controlar
 * e informa fallo en la consulta
 * @param navController
 * @param showButtonRetry: permite validar si se pinta en pantalla textBotton para volver atras
 */
@Composable
fun NoApiResponseView(
    navController: NavController,
    showButtonRetry: Boolean? = false) {
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
            text = stringResource(R.string.sorry),
            textAlign = TextAlign.Center,
            fontSize = 20.sp)
        Text(
            text = stringResource(R.string.api_response_failure),
            textAlign = TextAlign.Center)

        if (showButtonRetry == true)
            TextButton(onClick = {
                if(Utilities().isConnected())
                    navController.popBackStack()
            }) {
                Text(
                    text = stringResource(R.string.back),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp)
            }
    }
}

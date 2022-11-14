package com.example.melichallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challengemeli.R

/**
 * NoItemsView: composable creado para controlar
 * e informa que no se encontraron registros del item consultado
 */
@Composable
fun NoItemsView() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(R.color.gray)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = stringResource(R.string.we_did_not_find_publications),
            textAlign = TextAlign.Center,
            fontSize = 20.sp)
        Text(
            text = stringResource(R.string.description_not_find_publications),
            textAlign = TextAlign.Center)
    }
}
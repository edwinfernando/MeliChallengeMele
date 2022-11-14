package com.example.melichallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.challengemeli.R
import com.example.melichallenge.navigation.Destinations

/**
 * SearchToolbar: composable creado para pintar TopBar de busqueda
 * @param navController
 * @param state: permite obtener el valor del campo de busqueda
 * @param backButton: permite pintar en pantalla el icono volver atras
 * @param isNoItemsResult: permite validar si la palabra buscada no trajo registro y limpiar la pantalla
 * en el NavController
 */
@Composable
fun SearchToolbar(
    navController: NavController,
    state: MutableState<TextFieldValue>,
    backButton: Boolean? = false,
    isNoItemsResult: Boolean? = false
) {
    Column (modifier = Modifier
        .background(colorResource(R.color.yellow))
        .padding(bottom = 5.dp)
        .fillMaxWidth()
        .height(80.dp)
    ){
        var paddingStart = 14.dp
        if (backButton == true){
            paddingStart = 0.dp
        }
        Row(
            modifier = Modifier
                .padding(start = paddingStart)
                .padding(end = 14.dp)
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            if(backButton == true)
                IconButton(onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .background(colorResource(R.color.yellow))
                        .padding(start = 4.dp)
                        .padding(bottom = 2.dp)
                        .height(56.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            TextField(
                value = state.value,
                onValueChange = {
                    state.value = it
                },
                modifier = Modifier
                    .padding(0.dp)
                    .weight(1f)
                    .height(50.dp)
                    .defaultMinSize(minHeight = 48.dp),
                textStyle = TextStyle(color = Color.Black,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Left),
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_into_mercado_libre),
                        fontSize = 14.sp
                    )
                },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "") },
                trailingIcon = {
                    if (state.value != TextFieldValue("")) {
                        IconButton(onClick = { state.value = TextFieldValue("") }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(26.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.Black,
                    leadingIconColor = Color.Black,
                    trailingIconColor = Color.Black,
                    backgroundColor = colorResource(R.color.white),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if(isNoItemsResult == true)
                            navController.popBackStack()
                        if (state.value.text != "")
                            navController.navigate("${Destinations.SEARCH_ITEMS_SCREEN}/${state.value.text}")
                    }
                )
            )
        }
        Text(text = stringResource(R.string.author), modifier = Modifier.padding(start = 14.dp))
    }
}
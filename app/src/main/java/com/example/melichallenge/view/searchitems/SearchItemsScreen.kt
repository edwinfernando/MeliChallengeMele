package com.example.melichallenge.view.searchitems

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.challengemeli.R
import com.example.melichallenge.model.Items
import com.example.melichallenge.navigation.Destinations
import com.example.melichallenge.ui.theme.MeliChallengeTheme
import com.example.melichallenge.util.Utilities
import com.example.melichallenge.view.*

@Composable
fun SearchItemsScreen (
    searchItem: String,
    navController: NavController,
    viewModel: SearchItemsScreenViewModel = hiltViewModel()
){
    val lItemsSearched by viewModel.getItemsSearched(searchItem).observeAsState(emptyList())
    val totalPaging by viewModel.getTotalPaging().observeAsState(initial = -1)
    val isLoading by viewModel.isLoading.observeAsState(false)
    SearchItemsScreen(navController, lItemsSearched, searchItem, totalPaging, isLoading)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchItemsScreen (
    navController: NavController,
    lItemsSearched: List<Items>,
    searchedItem: String,
    totalPaging: Int,
    isLoading: Boolean
) {
    val textState = remember { mutableStateOf(TextFieldValue(searchedItem)) }
    var isNoItemResult = false
    if(totalPaging == 0)
        isNoItemResult = true
    Scaffold(
        topBar = { SearchToolbar(navController, textState, true, isNoItemResult) },
        bottomBar = { BottomBar(navController = navController)},
    ) {
        if(Utilities().isConnected())
            Column(modifier = Modifier.padding(0.dp)) {
                ListItemsSearchedView(navController, lItemsSearched, totalPaging, isLoading)
            }
        else NoInternetView(navController,true, searchedItem) // pinta en pantalla fallo en conexión
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun ListItemsSearchedView(
    navController: NavController,
    lItemsSearched: List<Items>,
    totalPaging: Int,
    isLoading: Boolean
) {
    Scaffold (
        topBar = {
            Card(
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.White)
            ) {
                if (lItemsSearched.isNotEmpty()){ // pinta en pantalla la cantidad de resultados por consulta
                    if (totalPaging > 2000)
                        Text(stringResource(R.string.plus_results),
                            modifier = Modifier.padding(8.dp))
                    else
                        Text(text = "$totalPaging " + stringResource(R.string.results),
                            modifier = Modifier.padding(8.dp))
                }
            }
        }
    ){
        if (isLoading && lItemsSearched.isEmpty()) { // pinta en pantalla el cargue de items
            val itemsLoading = 6
            LazyColumn(modifier = Modifier.padding(0.dp)) {
                items(count = itemsLoading) {
                    return@items LoadingItemList()
                }
            }
        }

        if (totalPaging == 0) // pinta en pantalla que no se encontraron resultados
            NoItemsView()

        var imageItem: String
        LazyColumn(modifier = Modifier.padding(0.dp)) {
            items(lItemsSearched) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("${Destinations.DETAILS_ITEMS_SCREEN}/${item.id}")
                        }
                ){
                    imageItem = Utilities().transformPictureUrl(item.thumbnail)
                    Card(modifier = Modifier
                        .padding(4.dp)
                        .width(120.dp)
                        .height(120.dp),
                        shape = RoundedCornerShape(16.dp),
                        // backgroundColor = colorResource(R.color.gray)
                    ){
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .memoryCacheKey(imageItem)
                                .diskCacheKey(imageItem)
                                .data(imageItem)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.placeholder),
                            error = painterResource(id = R.drawable.placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                        )
                    }
                    Column(modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                    ) {
                        Text(item.title, fontSize = 14.sp)
                        Text(Utilities().formatPrice(item.price), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Divider(startIndent = 2.dp, thickness = 2.dp, color = colorResource(R.color.gray))
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun SearchItemsScreenPreview() {
    MeliChallengeTheme {
        SearchItemsScreen(
            navController = rememberNavController(),
            lItemsSearched = arrayListOf(
                Items(
                "MLA1136376105", "Cámara De Seguridad Ip Gadnic Cm200w Full Hd 1080p Visión Nocturna Wifi App Deteccion De Movimiento Ios Android",
                "13799.0", "https://via.placeholder.com/540x300?text=yayocode.com", "234234234"
                )
            ),
            "",
            0,
            true
        )
    }
}
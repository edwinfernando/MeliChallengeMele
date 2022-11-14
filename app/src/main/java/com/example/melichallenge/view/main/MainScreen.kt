package com.example.melichallenge.view.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.challengemeli.R
import com.example.melichallenge.util.Utilities
import com.example.melichallenge.model.Categories
import com.example.melichallenge.navigation.Destinations
import com.example.melichallenge.ui.theme.MeliChallengeTheme
import com.example.melichallenge.view.NoInternetView
import com.example.melichallenge.view.SearchToolbar
import com.valentinilk.shimmer.shimmer

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val lCategories by viewModel.getCategories().observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    MainScreen(navController, lCategories, isLoading)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    lCategories: List<Categories>,
    isLoading: Boolean
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold (
        topBar = { SearchToolbar(navController, textState) },
        backgroundColor = colorResource(R.color.background)
    ){
        if (Utilities().isConnected()){
            val scrollState = rememberScrollState()
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .verticalScroll(scrollState)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ){
                    Image(painter = painterResource(R.drawable.logo_meli),contentDescription = "")
                }
                Spacer(modifier = Modifier.size(8.dp))
                ShowCategories(navController, lCategories, isLoading)
            }
        } else NoInternetView(navController, itemSearch = textState.value.text) // pinta en pantalla fallo en conexión
    }
}

@Composable
fun ShowCategories(
    navController: NavController,
    lCategories: List<Categories>,
    isLoading: Boolean
){
    Card(modifier = Modifier
        .height(500.dp)) {
        Column(modifier = Modifier.height(500.dp)) {
            Text(text = stringResource(R.string.categories), fontSize = 18.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .height(40.dp)
                    .padding(8.dp))
            Divider(startIndent = 2.dp, thickness = 1.dp, color = colorResource(R.color.gray))
            if (isLoading && lCategories.isEmpty()) { // pinta en la pantalla el cargue de las categorias
                val itemsLoading = 8
                LazyColumn(modifier = Modifier.padding(0.dp).height(500.dp)) {
                    items(count = itemsLoading) {
                        Row() {
                            return@items LoadingCategoryItem()
                        }
                    }
                }
            }

            LazyColumn(modifier = Modifier.padding(0.dp).height(500.dp)){
                items(lCategories) { categories ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("${Destinations.SEARCH_ITEMS_SCREEN}/${categories.name}")
                            }
                    ) {
                        Text(text = categories.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp))
                    }
                    Divider(startIndent = 2.dp, thickness = 1.dp, color = colorResource(R.color.gray))
                }
            }
        }
    }
}

@Composable
fun LoadingCategoryItem(){
    Box(modifier = Modifier.shimmer()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(40.dp)
                .background(Color.Gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MeliChallengeTheme {
        MainScreen(
            navController = rememberNavController(),
            lCategories = arrayListOf(
                Categories("MLA5725", "Accesorios para vehiculos")
            ),
            false
        )
    }
}

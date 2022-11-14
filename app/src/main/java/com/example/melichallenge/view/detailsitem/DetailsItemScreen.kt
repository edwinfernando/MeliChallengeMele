package com.example.melichallenge.view.detailsitem

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.challengemeli.R
import com.example.melichallenge.util.Utilities
import com.example.melichallenge.model.ItemDetailsAndDescription
import com.example.melichallenge.ui.theme.MeliChallengeTheme
import com.example.melichallenge.view.BottomBar
import com.example.melichallenge.view.LoadingDetailItem
import com.example.melichallenge.view.NoApiResponseView
import com.example.melichallenge.view.NoInternetView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun DetailsItemScreen(
    idItem: String,
    navController: NavController,
    viewModel: DetailsItemScreenViewModel = hiltViewModel()
) {
    val itemDescription by viewModel.getItemDescriptionById(idItem).observeAsState(initial = null)
    val isLoading by viewModel.isLoading.observeAsState(false)
    DetailsItemScreen(navController, itemDescription, isLoading)
}

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsItemScreen(
    navController: NavController,
    itemDescription: ItemDetailsAndDescription?,
    isLoading: Boolean
) {
    val state = rememberPagerState()
    val itemDetail = itemDescription?.itemDetails
    val description = itemDescription?.description

    Scaffold(
        topBar = { ToolbarCustom(navController) },
        bottomBar = { BottomBar(navController = navController) },
    ) {
        if (Utilities().isConnected()) {
            if(isLoading && itemDetail == null){
                LoadingDetailItem() // pinta en pantalla un cargue de la informacion del items
            }
            if(!isLoading &&itemDetail == null)
                NoApiResponseView(navController) // pinta en pantalla un fallo en la consulta por timeout
            itemDetail?.let {
                var imageUrl: String
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .fillMaxHeight()
                        .padding(8.dp)
                ) {
                    Text(itemDetail.title, fontSize = 16.sp)
                    Spacer(modifier = Modifier.size(8.dp))
                    HorizontalPager( // permite crear el slide de imagenes
                        state = state,
                        count = itemDetail.pictures?.size ?: 0,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    ) { page ->
                        imageUrl = itemDetail.pictures?.get(page)?.secure_url ?: ""
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .height(700.dp),
                        ) {
                            Text(
                                text = "${page + 1} / ${itemDetail.pictures?.size ?: 0}",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Right
                            )
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(500.dp),
                                elevation = 0.dp
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .memoryCacheKey(imageUrl)
                                        .diskCacheKey(imageUrl)
                                        .data(imageUrl)
                                        .crossfade(true)
                                        .build(),
                                    placeholder = painterResource(id = R.drawable.placeholder),
                                    error = painterResource(id = R.drawable.placeholder),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillHeight
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    Column(Modifier.padding(8.dp)) {
                        Text(
                            Utilities().formatPrice(itemDetail.price),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(modifier = Modifier.size(8.dp))
                        Text(text = stringResource(R.string.eDescription), fontSize = 16.sp)
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = description?.plain_text ?: "", fontSize = 12.sp)
                    }
                }
            }
        } else NoInternetView(navController,true) // pinta en pantalla fallo en conexión
    }
}

@Composable
fun ToolbarCustom( navController: NavController){
    Column (modifier = Modifier
        .background(colorResource(R.color.yellow))
        .padding(bottom = 5.dp)
        .fillMaxWidth()
        .height(60.dp)
    ){
        Row(
            modifier = Modifier
                .padding(start = 0.dp)
                .padding(end = 14.dp)
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
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
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .padding(0.dp)
                    .weight(1f)
                    .height(50.dp)
                    .defaultMinSize(minHeight = 48.dp),
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(R.color.yellow),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsItemScreenPreview() {
    MeliChallengeTheme {
        DetailsItemScreen(
            navController = rememberNavController(),
            itemDescription = null,
            true
        )
    }
}
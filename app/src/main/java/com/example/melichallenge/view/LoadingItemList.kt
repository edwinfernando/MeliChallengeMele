package com.example.melichallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.challengemeli.R
import com.valentinilk.shimmer.shimmer

/**
 * LoadingItemList: composable creado para simular el carge de la
 * de un item
 */
@Composable
fun LoadingItemList(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("loadingCard")
    ){
        ImageLoading()
        Column(modifier = Modifier
            .padding(8.dp)
            .weight(1f)
        ) {
            Box(modifier = Modifier.shimmer()){
                Column {
                    Box(
                        modifier = Modifier
                            .height(35.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)
                            .shimmer()
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(25.dp)
                            .background(Color.Gray)
                            .shimmer()
                    )
                }
            }
        }
    }
    Divider(startIndent = 2.dp, thickness = 1.dp, color = colorResource(R.color.gray))
}

@Composable
fun ImageLoading(){
    Box(modifier = Modifier.shimmer()){
        Box(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxHeight()
                .size(120.dp)
                .background(Color.Gray)
        )
    }
}
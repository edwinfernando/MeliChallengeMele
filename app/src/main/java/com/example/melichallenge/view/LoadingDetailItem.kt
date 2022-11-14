package com.example.melichallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

/**
 * LoadingDetailItem: composable creado para simular el carge de la
 * descripción y detalle de un item
 */
@Composable
fun LoadingDetailItem(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.shimmer()) {
            Box(
                modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Box(modifier = Modifier.shimmer()){
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray)
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Column(Modifier.padding(8.dp)) {
            Box(modifier = Modifier.shimmer()) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(25.dp)
                        .background(Color.Gray)
                )
            }
            Box(modifier = Modifier.size(8.dp))
            Box(modifier = Modifier.shimmer()) {
                Box(
                    modifier = Modifier
                        .height(25.dp)
                        .fillMaxWidth()
                        .background(Color.Gray)
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Box(modifier = Modifier.shimmer()) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(Color.Gray)
                )
            }
        }
    }
}
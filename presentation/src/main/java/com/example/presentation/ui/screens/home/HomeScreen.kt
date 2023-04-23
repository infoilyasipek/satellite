package com.example.presentation.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(true)
@Destination
@Composable
fun HomeScreen() {
    Text(text = " HomeScreen ")
}
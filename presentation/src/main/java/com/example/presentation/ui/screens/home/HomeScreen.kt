package com.example.presentation.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.ui.base.satelliteViewModel
import com.example.presentation.ui.composables.Center
import com.example.presentation.ui.composables.SatelliteLoadingCircularIndicator
import com.example.presentation.ui.composables.SatelliteSearchBar
import com.example.presentation.ui.screens.home.HomeContract.*
import com.example.presentation.ui.screens.home.composables.SatelliteItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(true)
@Destination
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = satelliteViewModel()
    val (uiState, onIntent, events) = viewModel

    HomeScreen(
        uiState = uiState,
        onIntent = onIntent
    )
}

@Composable
fun HomeScreen(
    uiState: UiState,
    onIntent: (Intent) -> Unit
) {
    Scaffold(
        topBar = {
            SatelliteSearchBar(
                searchText = uiState.searchQuery,
                onSearchTextChange = { onIntent(Intent.Search(it)) },
                modifier = Modifier.padding(16.dp),
                hint = stringResource(id = R.string.search_hint)
            )
        }
    ) {
        if (uiState.isNoSatellitesFoundForSearchQuery) {
            Center {
                Text(stringResource(id = R.string.no_satellites_found_for_search_query))
            }
        }

        Column(modifier = Modifier.padding(it)) {

            if (uiState.isLoading) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SatelliteLoadingCircularIndicator()
                }
            }

            LazyColumn() {
                items(uiState.filteredSatellites.size) { index ->
                    SatelliteItem(
                        satellite = uiState.filteredSatellites[index],
                        onClick = { /*onIntent(Intent.SelectSatellite(satellite))*/ }
                    )
                    if (index != uiState.filteredSatellites.lastIndex) {
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}

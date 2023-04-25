package com.example.presentation.ui.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.satellites.model.Satellite
import com.example.presentation.R
import com.example.presentation.ui.base.satelliteViewModel
import com.example.presentation.ui.screens.detail.SatelliteDetailContract.Intent
import com.example.presentation.ui.screens.detail.SatelliteDetailContract.UiState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SatelliteDetailScreen(
    navigator: DestinationsNavigator,
    satellite: Satellite,
) {
    val viewModel: SatelliteDetailViewModel = satelliteViewModel(initByDefault = false)
    val (uiState, onIntent, events) = viewModel

    SatelliteDetailScreen(
        satellite = satellite,
        uiState = uiState,
        onIntent = onIntent
    )

    LaunchedEffect(Unit) {
        onIntent(Intent.Init(satellite.id))

        events.collect { event ->
            when (event) {
                is SatelliteDetailContract.Event.NavigateBack -> navigator.navigateUp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SatelliteDetailScreen(
    satellite: Satellite,
    uiState: UiState,
    onIntent: (Intent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                title = {
                    Text(text = stringResource(id = R.string.satellite_detail_screen_title))
                },
                navigationIcon = {
                    BackIcon(onClick = { onIntent(Intent.NavigateBack) })
                }
            )
        }
    ) {
        val satelliteDetail = uiState.satelliteDetail ?: return@Scaffold
        val satellitePosition = uiState.satellitePosition ?: return@Scaffold

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = satellite.name, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = satelliteDetail.firstFlight, style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(32.dp))


            TitledDescriptionRow(
                title = stringResource(id = R.string.height_and_mess_title),
                description = "${satelliteDetail.height}/${satelliteDetail.mass}"
            )

            TitledDescriptionRow(
                title = stringResource(id = R.string.cost_title),
                description = satelliteDetail.costPerLaunch.toString()
            )

            TitledDescriptionRow(
                title = stringResource(id = R.string.last_position_title),
                description = stringResource(
                    id = R.string.last_position_description,
                    satellitePosition.posX.toString(),
                    satellitePosition.posY.toString()
                )
            )
        }
    }
}


@Composable
private fun TitledDescriptionRow(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$title:",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}


@Composable
private fun BackIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Rounded.ArrowBack,
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .padding(12.dp)
    )
}

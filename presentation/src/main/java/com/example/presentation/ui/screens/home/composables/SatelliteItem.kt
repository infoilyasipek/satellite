package com.example.presentation.ui.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.satellites.model.Satellite
import com.example.domain.satellites.model.satelliteSample
import com.example.presentation.R
import com.example.presentation.ui.theme.SatelliteTheme

@Composable
fun SatelliteItem(
    satellite: Satellite,
    onClick: (Satellite) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(satellite) }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 32.dp),
            contentAlignment = Alignment.CenterEnd,
        ) {
            val color = if (satellite.isActive) Color.Green else Color.Red
            SatelliteStatusCircleIndicator(color)
        }

        Surface(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {

            Column {
                Text(text = satellite.name)

                Text(
                    text = if (satellite.isActive) {
                        stringResource(id = R.string.active)
                    } else {
                        stringResource(id = R.string.passive)
                    }
                )
            }
        }
    }
}

@Composable
private fun SatelliteStatusCircleIndicator(color: Color) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(color)
    ) {}
}

@Preview
@Composable
fun SatelliteItemPreview() {
    SatelliteTheme {
        SatelliteItem(
            satellite = satelliteSample,
            onClick = {}
        )
    }
}
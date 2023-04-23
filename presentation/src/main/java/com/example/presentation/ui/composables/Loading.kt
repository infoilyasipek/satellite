package com.example.presentation.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.presentation.ui.theme.SatelliteTheme

@Composable
fun SatelliteCircularLoadingDialog() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Center {
            Surface(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.size(100.dp),
                shadowElevation = 8.dp
            ) {
                SatelliteLoadingCircularIndicator()
            }
        }
    }
}

@Composable
fun SatelliteLoadingCircularIndicator(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier.padding(24.dp)
    )
}

@Preview
@Composable
fun SatelliteCircularLoadingDialogPreview() {
    SatelliteTheme {
        SatelliteCircularLoadingDialog()
    }
}
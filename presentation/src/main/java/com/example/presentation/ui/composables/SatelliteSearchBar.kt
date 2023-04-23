package com.example.presentation.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.ui.theme.SatelliteTheme

@Composable
fun SatelliteSearchBar(
    searchText: String,
    onSearchTextChange: ((String) -> Unit),
    modifier: Modifier = Modifier,
    hint: String = ""
) {
    Surface(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {

            Icon(imageVector = Icons.Rounded.Search, contentDescription = "")

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = searchText,
                onValueChange = { onSearchTextChange.invoke(it) },
                modifier = Modifier
                    .weight(1f),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        if (searchText.isEmpty()) {
                            Text(
                                text = hint,
                                fontSize = 14.sp,
                            )
                        }
                    }
                    innerTextField()
                }
            )

            if (searchText.isNotEmpty()) {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(18.dp)
                        .clickable { onSearchTextChange.invoke("") }
                )
            }
        }
    }
}

@Preview
@Composable
fun SatelliteSearchBarPreview() {
    SatelliteTheme {
        SatelliteSearchBar(
            searchText = "Search Text",
            onSearchTextChange = {},
            modifier = Modifier.padding(horizontal = 16.dp),
            hint = "Hint",
        )
    }
}


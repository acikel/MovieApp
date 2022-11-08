package com.example.crewmovies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crewmovies.R
import com.example.crewmovies.ui.theme.boldTitle

@Composable
fun ListsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Text(
                text = stringResource(id = R.string.lists),
                style = MaterialTheme.typography.boldTitle,
                modifier = Modifier
                    .padding(start = 10.dp, top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun ListsScreenPreview() {
    ListsScreen()
}
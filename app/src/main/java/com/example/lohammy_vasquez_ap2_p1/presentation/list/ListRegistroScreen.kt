package com.example.lohammy_vasquez_ap2_p1.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ListRegistroScreen (
    viewModel: ListRegistroViewModel = hiltViewModel(),
    onNew: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.navigateToCrear) {
        if (state.navigateToCrear) {
            onNew()
            viewModel.onNewDone()
        }
    }

    ListRegistroBody(
        onNew = { viewModel.onNew() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListRegistroBody(
    onNew: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pantalla Principal") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNew
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ir a segunda pantalla"
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Presiona el botón para navegar",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

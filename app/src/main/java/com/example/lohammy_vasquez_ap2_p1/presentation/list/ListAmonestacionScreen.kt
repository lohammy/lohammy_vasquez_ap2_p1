package com.example.lohammy_vasquez_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lohammy_vasquez_ap2_p1.domain.model.Amonestacion
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAmonestacionScreen(
    viewModel: ListAmonestacionViewModel = hiltViewModel(),
    onEdit: (Int) -> Unit,
    onAdd: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val filter by viewModel.filter.collectAsStateWithLifecycle()

    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Listado de Amonestaciones") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAdd,
                modifier = Modifier.padding(bottom = 60.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Amonestacion")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = filter,
                onValueChange = viewModel::onFilterChange,
                label = { Text("Buscar por Nombre o Razon") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.amonestaciones) { amonestacion ->
                    AmonestacionItem(
                        amonestacion = amonestacion,
                        onEdit = { onEdit(amonestacion.amonestacionId ?: 0) },
                        formatMonto = { currencyFormat.format(it) }
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Registros: ${uiState.totalCount}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Monto Total: ${currencyFormat.format(uiState.totalMonto)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun AmonestacionItem(
    amonestacion: Amonestacion,
    onEdit: () -> Unit,
    formatMonto: (Double) -> String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = amonestacion.nombres,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Razon: ${amonestacion.razon}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Monto: ${formatMonto(amonestacion.monto)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = onEdit) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

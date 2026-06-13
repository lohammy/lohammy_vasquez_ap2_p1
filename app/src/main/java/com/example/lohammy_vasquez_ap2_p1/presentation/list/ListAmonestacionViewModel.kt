package com.example.lohammy_vasquez_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lohammy_vasquez_ap2_p1.domain.model.Amonestacion
import com.example.lohammy_vasquez_ap2_p1.domain.usecase.GetAmonestacionesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListAmonestacionViewModel @Inject constructor(
    private val getAmonestacionesUseCase: GetAmonestacionesUseCase
) : ViewModel() {

    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    private val _amonestaciones = getAmonestacionesUseCase()

    val uiState: StateFlow<AmonestacionListUiState> = combine(
        _amonestaciones, _filter
    ) { amonestaciones, query ->
        val filtered = amonestaciones.filter {
            query.isEmpty() || 
            it.nombres.contains(query, ignoreCase = true) || 
            it.razon.contains(query, ignoreCase = true)
        }
        AmonestacionListUiState(
            amonestaciones = filtered,
            totalCount = filtered.size,
            totalMonto = filtered.sumOf { it.monto }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AmonestacionListUiState()
    )

    fun onFilterChange(value: String) {
        _filter.value = value
    }
}

data class AmonestacionListUiState(
    val amonestaciones: List<Amonestacion> = emptyList(),
    val totalCount: Int = 0,
    val totalMonto: Double = 0.0
)

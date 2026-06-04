package com.example.lohammy_vasquez_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ListRegistroViewModel @Inject constructor(
): ViewModel() {
    private val _state = MutableStateFlow(ListRegistroUiState())
    val state: StateFlow<ListRegistroUiState> = _state.asStateFlow()

    fun onNew() {
        _state.update { it.copy(navigateToCrear = true) }
    }

    fun onNewDone() {
        _state.update { it.copy(navigateToCrear = false) }
    }
}

data class ListRegistroUiState(
    val navigateToCrear: Boolean = false
)

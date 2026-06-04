package com.example.lohammy_vasquez_ap2_p1.presentation.edit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditRegistroViewModel @Inject constructor(
): ViewModel() {
    private val _state = MutableStateFlow(EditRegistroUiState())
    val state: StateFlow<EditRegistroUiState> = _state.asStateFlow()
}

data class EditRegistroUiState(
    val success: Boolean = false
)

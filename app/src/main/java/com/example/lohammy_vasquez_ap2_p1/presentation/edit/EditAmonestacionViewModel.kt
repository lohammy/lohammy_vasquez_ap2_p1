package com.example.lohammy_vasquez_ap2_p1.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lohammy_vasquez_ap2_p1.domain.model.Amonestacion
import com.example.lohammy_vasquez_ap2_p1.domain.usecase.DeleteAmonestacionUseCase
import com.example.lohammy_vasquez_ap2_p1.domain.usecase.GetAmonestacionesUseCase
import com.example.lohammy_vasquez_ap2_p1.domain.usecase.UpsertAmonestacionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class EditAmonestacionViewModel @Inject constructor(
    private val getAmonestacionesUseCase: GetAmonestacionesUseCase,
    private val upsertAmonestacionUseCase: UpsertAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditAmonestacionUiState())
    val uiState = _uiState.asStateFlow()

    private val numberFormat = NumberFormat.getNumberInstance(Locale.US).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    fun loadAmonestacion(id: Int) {
        viewModelScope.launch {
            if (id > 0) {
                val amonestacion = getAmonestacionesUseCase(id)
                amonestacion?.let {
                    _uiState.update { state ->
                        state.copy(
                            amonestacionId = it.amonestacionId,
                            nombres = it.nombres,
                            razon = it.razon,
                            monto = formatMonto(it.monto)
                        )
                    }
                }
            }
        }
    }

    fun onNombresChange(nombres: String) {
        _uiState.update { it.copy(nombres = nombres, nombresError = null) }
    }

    fun onRazonChange(razon: String) {
        _uiState.update { it.copy(razon = razon, razonError = null) }
    }

    fun onMontoChange(monto: String) {
        val cleanMonto = monto.replace(",", "")
        if (cleanMonto.isEmpty() || cleanMonto.toDoubleOrNull() != null || cleanMonto == ".") {
            _uiState.update { it.copy(monto = cleanMonto, montoError = null) }
        }
    }

    private fun formatMonto(monto: Double): String {
        return numberFormat.format(monto)
    }

    fun save() {
        if (!validate()) return

        viewModelScope.launch {
            upsertAmonestacionUseCase(
                Amonestacion(
                    amonestacionId = _uiState.value.amonestacionId,
                    nombres = _uiState.value.nombres,
                    razon = _uiState.value.razon,
                    monto = _uiState.value.monto.toDoubleOrNull() ?: 0.0
                )
            )
            _uiState.update { it.copy(isSuccess = true) }
        }
    }

    fun delete() {
        viewModelScope.launch {
            _uiState.value.amonestacionId?.let { id ->
                deleteAmonestacionUseCase(
                    Amonestacion(
                        amonestacionId = id,
                        nombres = _uiState.value.nombres,
                        razon = _uiState.value.razon,
                        monto = _uiState.value.monto.toDoubleOrNull() ?: 0.0
                    )
                )
                _uiState.update { it.copy(isSuccess = true) }
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (_uiState.value.nombres.isBlank()) {
            _uiState.update { it.copy(nombresError = "El nombre es obligatorio") }
            isValid = false
        }
        if (_uiState.value.razon.isBlank()) {
            _uiState.update { it.copy(razonError = "La razon es obligatoria") }
            isValid = false
        }
        val cleanMonto = _uiState.value.monto.replace(",", "")
        val montoValue = cleanMonto.toDoubleOrNull()
        if (montoValue == null || montoValue <= 0) {
            _uiState.update { it.copy(montoError = "El monto debe ser un numero mayor a 0") }
            isValid = false
        }
        return isValid
    }
}

data class EditAmonestacionUiState(
    val amonestacionId: Int? = null,
    val nombres: String = "",
    val nombresError: String? = null,
    val razon: String = "",
    val razonError: String? = null,
    val monto: String = "",
    val montoError: String? = null,
    val isSuccess: Boolean = false
)

package com.mvince.compose.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvince.compose.repository.DetailsRepository
import com.mvince.compose.ui.Argument
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val username: String? = savedStateHandle[Argument.USERNAME]

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState>
        get() = _uiState

    init {
        username?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val detail = detailsRepository.getUserDetails(it)
                withContext(Dispatchers.Main) {
                    _uiState.update { it.copy(detail = detail, offline = false) }
                }
            }
        }
    }
}

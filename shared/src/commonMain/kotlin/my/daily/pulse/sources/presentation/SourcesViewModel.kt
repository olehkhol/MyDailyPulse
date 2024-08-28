package my.daily.pulse.sources.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import my.daily.pulse.sources.application.SourcesUseCase

class SourcesViewModel(
    private val useCase: SourcesUseCase
) : ViewModel() {

    private val _sourcesState = MutableStateFlow(SourcesState(loading = true))
    val sourcesState = _sourcesState.asStateFlow()

    init {
        getSources()
    }

    private fun getSources() {
        viewModelScope.launch {
            val fetchedSources = useCase.getSources()

            _sourcesState.emit(SourcesState(sources = fetchedSources))
        }
    }
}

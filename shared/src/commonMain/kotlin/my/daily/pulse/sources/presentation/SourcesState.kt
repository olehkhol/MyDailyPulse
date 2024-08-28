package my.daily.pulse.sources.presentation

import my.daily.pulse.sources.application.Source

data class SourcesState(
    val sources: List<Source> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
)

package com.dicoding.myulamacompose.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dicoding.myulamacompose.data.UlamaRepository
import com.dicoding.myulamacompose.model.Ulama
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeVM(private val ulamaRepository: UlamaRepository) : ViewModel() {
    private val _groupUlama = MutableStateFlow(
        ulamaRepository.getUlama()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupUlama: StateFlow<Map<Char, List<Ulama>>> get() = _groupUlama

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupUlama.value = ulamaRepository.searchUlama(newQuery)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}
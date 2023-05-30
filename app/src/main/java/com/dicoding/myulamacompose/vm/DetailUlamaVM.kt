package com.dicoding.myulamacompose.vm

import androidx.lifecycle.ViewModel
import com.dicoding.myulamacompose.data.UlamaRepository
import com.dicoding.myulamacompose.model.Ulama
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailUlamaVM(private val ulamaRepository: UlamaRepository) : ViewModel() {
    fun getDataUlama(idUlama: String): StateFlow<Ulama> = MutableStateFlow(
        ulamaRepository.getUlamaById(idUlama)
    ).asStateFlow()
}
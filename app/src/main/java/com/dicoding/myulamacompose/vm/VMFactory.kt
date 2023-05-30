package com.dicoding.myulamacompose.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.myulamacompose.data.UlamaRepository

class VMFactory(private val ulamaRepository: UlamaRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeVM::class.java)) {
            return HomeVM(ulamaRepository) as T
        } else if (modelClass.isAssignableFrom(DetailUlamaVM::class.java)) {
            return DetailUlamaVM(ulamaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
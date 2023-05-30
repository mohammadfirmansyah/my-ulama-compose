package com.dicoding.myulamacompose.data

import com.dicoding.myulamacompose.model.DataUlama
import com.dicoding.myulamacompose.model.Ulama

public class UlamaRepository {
    fun getUlama():List<Ulama>
    {
        return DataUlama.ulama
    }

    fun searchUlama(query: String): List<Ulama> {
        return DataUlama.ulama.filter { ulama ->
            ulama.name.contains(query, ignoreCase = true)
        }
    }

    fun getUlamaById(id: String): Ulama {
        return DataUlama.ulama.find {
            it.id == id
        } as Ulama
    }
}
package com.example.mystore.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class AppViewModel : ViewModel() {
    private val _shouldItemBeVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val shouldItemBeVisible: MutableStateFlow<Boolean> = _shouldItemBeVisible

    fun setShouldItemBeVisible(shouldItemBeVisible: Boolean) {
        _shouldItemBeVisible.value = shouldItemBeVisible
    }
}

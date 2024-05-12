package com.example.mystore.model

import androidx.compose.runtime.Composable
import com.example.mystore.Section

data class SectionInfo(
    val section: @Composable () -> Unit,
    val sectionName: Section = Section.NONE,
)
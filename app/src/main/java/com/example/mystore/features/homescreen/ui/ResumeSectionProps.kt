package com.example.mystore.features.homescreen.ui

import com.example.mystore.features.homescreen.model.Resume

data class ResumeSectionProps(
    val resume: Resume?,
    val shouldItemBeVisible: Boolean,
    val onEmptyStateImageClicked: (route: String, screen: String) -> Unit
)

package com.example.mystore.features.homescreen.model

data class ResumeSectionProps(
    val resume: Resume?,
    val shouldItemBeVisible: Boolean,
    val onEmptyStateImageClicked: (route: String, screen: String) -> Unit
)

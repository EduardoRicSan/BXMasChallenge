package com.tech.design_system.common.model

data class BXMasDialogMessage(
    val title: String,
    val message: String,
    val confirmLabel: String,
    val onConfirm: () -> Unit,
    val dismissLabel: String? = null,
    val onDismiss: (() -> Unit)? = null
)
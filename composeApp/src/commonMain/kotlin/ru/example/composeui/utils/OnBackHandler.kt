package ru.example.composeui.utils

import androidx.compose.runtime.Composable

@Composable
expect fun OnBackHandler(enabled: Boolean = true, onBack: () -> Unit)
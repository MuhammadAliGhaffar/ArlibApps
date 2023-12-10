package com.example.arlibapps.feature.dashboard

import com.example.arlibapps.data.model.Medicine

data class DashboardUiState(
    var medicines: List<Medicine> = listOf(),
)
package com.unfortunatefortunes.model

import com.google.type.Date

data class User (
    val email: String? = null,
    val gender: String? = null,
    val DOB: Date? = null
)
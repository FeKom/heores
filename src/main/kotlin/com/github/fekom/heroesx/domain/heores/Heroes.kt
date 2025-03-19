package com.github.fekom.heroesx.domain.heores

data class Heroes(
    val id: Long? = null,
    val nick: String,
    val person: String,
    val description: String,
    val history: String
)
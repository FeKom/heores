package com.github.fekom.heroesx.application.web.resource.request

import com.github.fekom.heroesx.domain.heores.Heroes
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class HeroesRequest(
    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val nick: String,

    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val person: String,

    val description: String? = null,
    val history: String? = null
) {
    fun toHeroes() = Heroes(
        nick = nick,
        person = person,
        description = description,
        history = history
    )

    companion object{
        fun to(id: Long?, req: HeroesRequest) = Heroes(
            id = id,
            nick = req.nick,
            person = req.person,
            description = req.description,
            history = req.history
        )
    }
}
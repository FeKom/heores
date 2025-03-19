package com.github.fekom.heroesx.application.web.resource.response

import com.github.fekom.heroesx.domain.heores.Heroes

data class HeroesResponse(
    val id: Long? = null,
    val nick: String,
    val person: String,
    val description: String?,
    val history: String?
) {
    companion object {
        fun from(heroes: Heroes) = HeroesResponse(
            id = heroes.id,
            nick = heroes.nick,
            person = heroes.person,
            description = heroes.description,
            history = heroes.history
        )
    }
}

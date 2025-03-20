package com.github.fekom.heroesx.resource.heroes

import com.github.fekom.heroesx.domain.heores.Heroes
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class HeroesEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    val nick: String,
    @Column(nullable = false)
    val person: String,
    val description: String?,
    val history: String?
) {
    fun toHeroes() =  Heroes(id,nick,person,description,history)

    companion object {
        fun from(heroes: Heroes) = HeroesEntity(
            id = heroes.id,
            nick = heroes.nick,
            person = heroes.person,
            description = heroes.description,
            history = heroes.history

        )
    }
}
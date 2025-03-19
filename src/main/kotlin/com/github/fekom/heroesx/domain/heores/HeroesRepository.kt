package com.github.fekom.heroesx.domain.heores

interface HeroesRepository {
    fun getDetails(id: Long): Heroes
    fun getHeroes(): List<Heroes>
    fun create(heroes: Heroes): Heroes
    fun delete(id: Long)
    fun update(heroes: Heroes): Heroes
}
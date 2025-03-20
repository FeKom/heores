package com.github.fekom.heroesx.resource.heroes

import com.github.fekom.heroesx.domain.heores.Heroes
import com.github.fekom.heroesx.domain.heores.HeroesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class HeroesRepositoryImpl(
    @Autowired private val repository: HeroesEntityRepository
) : HeroesRepository {
    override fun getDetails(id: Long): Heroes? =
        repository.findByIdOrNull(id)?.toHeroes()

    override fun getHeroes(): List<Heroes> =
        repository.findAll().map{ it.toHeroes() }

    override fun create(heroes: Heroes): Heroes =
        repository.save(HeroesEntity.from(heroes)).toHeroes()

    override fun delete(id: Long) = repository.deleteById(id)

    override fun update(heroes: Heroes): Heroes  =
        repository.save(HeroesEntity.from(heroes)).toHeroes()
}
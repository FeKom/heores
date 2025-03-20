package com.github.fekom.heroesx.resource.heroes

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HeroesEntityRepository : JpaRepository<HeroesEntity, Long> {
}
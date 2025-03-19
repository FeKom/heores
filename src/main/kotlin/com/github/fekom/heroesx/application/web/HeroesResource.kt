package com.github.fekom.heroesx.application.web

import com.github.fekom.heroesx.application.web.resource.request.HeroesRequest
import com.github.fekom.heroesx.application.web.resource.response.HeroesResponse
import com.github.fekom.heroesx.domain.heores.HeroesRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

private const val API_PATH = "v1/api/heroes"

@RestController
@RequestMapping(value = [API_PATH])
class HeroesResource(
    @Autowired private val repository: HeroesRepository
) {
    @GetMapping
    //Get All heroes
    fun getHeroes() = repository.getHeroes()
        .map{ HeroesResponse.from(it) }
        .let { ResponseEntity.ok().body(it) }

    @GetMapping("{id}/detail")
    //Get Heroes by id
    fun getHeroesDetails(@PathVariable("id") id: Long) =
        repository.getDetails(id)?.let {
            ResponseEntity.ok().body(HeroesResponse.from(it))
        }?: ResponseEntity.notFound().build<Void>()

    @PostMapping
    //create hero
    fun createHero(@Valid @RequestBody req : HeroesRequest) =
        req.toHeroes().run {
            repository.create(this)
        }.let {
            ResponseEntity.created(URI("$API_PATH/${it.id}"))
                .body((HeroesResponse.from(it)))
        }
    @PutMapping("{id}")
    fun updateHero(@Valid @RequestBody req: HeroesRequest, @PathVariable("id") id: Long) =
        repository.getDetails(id)?.let {
            HeroesRequest.to(it.id, req).apply {
                repository.update(this)
            }.let { heroes ->
                ResponseEntity.accepted().body(HeroesResponse.from(heroes))
            }
        }?: ResponseEntity.notFound().build<Void>()

    @DeleteMapping("{id}")
    fun deleteHero(@PathVariable("id")id: Long) =
        repository.delete(id).let {
            ResponseEntity.accepted().build<Void>()

        }
}
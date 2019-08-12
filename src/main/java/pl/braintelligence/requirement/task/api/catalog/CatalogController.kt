package pl.braintelligence.requirement.task.api.movie

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.domain.core.catalog.CatalogService
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog

@RestController
@RequestMapping("/catalogs")
internal class CatalogController(
        private val catalogService: CatalogService
) : ApiCatalogController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createCatalog(
            @RequestBody newCatalog: NewCatalog
    ) = catalogService.catalogService(newCatalog)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getAllCatalogs(

    ): List<DbCatalog> = catalogService.getAllCatalogs()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    override fun updateCatalog(
            @RequestBody catalogToUpdate: CatalogToUpdate
    ) = catalogService.updateCatalog(catalogToUpdate)

}

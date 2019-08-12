package pl.braintelligence.requirement.task.api.movie

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogDto
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.domain.core.catalog.CatalogService

@RestController
@RequestMapping("/catalogs")
internal class CatalogController(
        private val catalogService: CatalogService
) : ApiCatalogController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createCatalog(newCatalog: NewCatalog) =
            catalogService.catalogService()

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getAllCatalogs(): CatalogDto =
            catalogService.getAllCatalogs()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    override fun updateCatalog(catalogToUpdate: CatalogToUpdate) =
            catalogService.updateCatalog(catalogToUpdate)

}

package pl.braintelligence.requirement.task.api.catalog

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.domain.core.catalog.CatalogService

@RestController
@RequestMapping("/internal/catalogs")
internal class InternalCatalogController(
        private val catalogService: CatalogService
) : ApiInternalCatalogController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createCatalog(
            @RequestBody newCatalog: NewCatalog
    ) = catalogService.catalogService(newCatalog)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    override fun updateCatalog(
            @RequestBody catalogToUpdate: CatalogToUpdate
    ) = catalogService.updateCatalog(catalogToUpdate)

}

package pl.braintelligence.requirement.task.api.catalog

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.braintelligence.requirement.task.domain.core.catalog.Catalog
import pl.braintelligence.requirement.task.domain.core.catalog.CatalogService

@RestController
@RequestMapping("/api/catalogs")
internal class CatalogController(
        private val catalogService: CatalogService
) : ApiCatalogController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getAllCatalogs(
    ): List<Catalog> = catalogService.getAllCatalogs()

}

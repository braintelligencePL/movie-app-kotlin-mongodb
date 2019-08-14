package pl.braintelligence.requirement.task.api.catalog

import io.swagger.annotations.ApiOperation
import pl.braintelligence.requirement.task.domain.core.catalog.Catalog

interface ApiCatalogController {

    @ApiOperation(value = "Get all catalogs")
    fun getAllCatalogs(): List<Catalog>

}

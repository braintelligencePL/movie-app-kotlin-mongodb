package pl.braintelligence.requirement.task.api.catalog

import io.swagger.annotations.ApiOperation
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.domain.core.catalog.Catalog

interface ApiCatalogController {

    @ApiOperation(value = "Create new empty catalog")
    fun createCatalog(newCatalog: NewCatalog)

    @ApiOperation(value = "Get all catalogs")
    fun getAllCatalogs(): List<Catalog>

    @ApiOperation(value = "Update catalog")
    fun updateCatalog(catalogToUpdate: CatalogToUpdate)

}

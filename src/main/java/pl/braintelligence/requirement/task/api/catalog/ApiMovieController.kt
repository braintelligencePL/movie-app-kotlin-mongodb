package pl.braintelligence.requirement.task.api.movie

import io.swagger.annotations.ApiOperation
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog

interface ApiCatalogController {

    @ApiOperation(value = "Create new catalog of movies")
    fun createCatalog(newCatalog: NewCatalog)

    @ApiOperation(value = "Get all catalogs")
    fun getAllCatalogs(): List<DbCatalog>

    @ApiOperation(value = "Update catalog")
    fun updateCatalog(catalogToUpdate: CatalogToUpdate)

}

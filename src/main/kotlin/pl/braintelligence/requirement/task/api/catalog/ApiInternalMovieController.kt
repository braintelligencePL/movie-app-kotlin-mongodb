package pl.braintelligence.requirement.task.api.catalog

import io.swagger.annotations.ApiOperation
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog

interface ApiInternalCatalogController {

    @ApiOperation(value = "Create new empty catalog")
    fun createCatalog(newCatalog: NewCatalog)

    @ApiOperation(value = "Update catalog")
    fun updateCatalog(catalogToUpdate: CatalogToUpdate)

}

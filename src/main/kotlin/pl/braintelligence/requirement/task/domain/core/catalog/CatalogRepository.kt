package pl.braintelligence.requirement.task.domain.core.catalog

import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog


interface CatalogRepository {

    fun save(catalogName: String)

    fun findAll(): List<Catalog>

    fun  updateCatalog(catalogToUpdate: DbCatalog)

}

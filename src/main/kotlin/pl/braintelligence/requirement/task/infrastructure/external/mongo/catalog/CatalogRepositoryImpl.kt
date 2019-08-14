package pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog

import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import pl.braintelligence.requirement.task.domain.core.catalog.Catalog
import pl.braintelligence.requirement.task.domain.core.catalog.CatalogRepository
import pl.braintelligence.requirement.task.infrastructure.error.EntityAlreadyExistException
import pl.braintelligence.requirement.task.infrastructure.error.ErrorCode
import pl.braintelligence.requirement.task.infrastructure.error.MissingEntityException
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog

@Repository
interface ICatalogMongoCatalogRepository : MongoRepository<DbCatalog, String> {
    fun save(catalog: String)
    fun existsByName(name: String): Boolean
}

@Component
open class CatalogRepositoryImpl(
        private val mongo: ICatalogMongoCatalogRepository,
        private val mongoOperations: MongoOperations
) : CatalogRepository {

    override fun findAll(): List<Catalog> = DbCatalog.toCatalogs(mongo.findAll())

    override fun save(catalogName: String) {
        if (mongo.existsByName(catalogName))
            throw EntityAlreadyExistException("Catalog with name='$catalogName' already exist", ErrorCode.ENTITY_ALREADY_EXIST)

        mongo.save(DbCatalog(catalogName))
    }

    override fun updateCatalog(dbCatalog: DbCatalog) {
        if (!mongo.existsByName(dbCatalog.name))
            throw MissingEntityException("Catalog with name='${dbCatalog.name}' doesnt't exists", ErrorCode.MISSING_ENTITY)

        val query = Query()
        query.addCriteria(Criteria.where("name").`is`(dbCatalog.name))

        val update = Update.update("movies", dbCatalog.movies)

        mongoOperations.updateFirst(query, update, DbCatalog::class.java)

    }
}

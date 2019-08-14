package pl.braintelligence.requirement.task.domain.exceptions

open class DomainException(message: String) : Exception(message)

internal class InvalidRatingException(message: String) : DomainException(message)
internal class InvalidPriceException(message: String) : DomainException(message)

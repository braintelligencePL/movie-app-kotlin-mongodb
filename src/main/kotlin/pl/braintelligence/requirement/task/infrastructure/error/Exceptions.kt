package pl.braintelligence.requirement.task.infrastructure.error

class ApiResponseException(message: String, code: ErrorCode) : RuntimeException(message)


class EntityAlreadyExistException(message: String, code: ErrorCode) : RuntimeException(message)

class MissingEntityException(message: String, code: ErrorCode) : RuntimeException(message)

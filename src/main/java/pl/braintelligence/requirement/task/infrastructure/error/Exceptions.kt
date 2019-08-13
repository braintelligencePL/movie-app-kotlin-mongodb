package pl.braintelligence.requirement.task.infrastructure.error

class ApiResponseException(message: String, code: ErrorCode) : RuntimeException(message)

class EntityAlreadyExist(message: String, code: ErrorCode) : RuntimeException(message)

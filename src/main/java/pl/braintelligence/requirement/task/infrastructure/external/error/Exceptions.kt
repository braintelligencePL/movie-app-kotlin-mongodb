package pl.braintelligence.requirement.task.infrastructure.external.error

class ApiResponseException(message: String, code: ErrorCode) : RuntimeException(message)

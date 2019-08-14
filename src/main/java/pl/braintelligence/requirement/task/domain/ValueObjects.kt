package pl.braintelligence.requirement.task.domain

import java.math.BigDecimal
import java.util.Currency

// Value Objects

data class Price(
        val value: BigDecimal,
        val currency: Currency
)

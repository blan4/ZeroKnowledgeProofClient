package org.seniorsigan.qrauthenticatorclient.persistence

import java.util.*

data class AccountModel(
        var id: Long = 0,
        val name: String,
        val domain: String,
        val tokens: List<String>,
        val currentToken: Int = 0,
        val updatedAt: Date = Date(),
        val createdAt: Date = Date()
)
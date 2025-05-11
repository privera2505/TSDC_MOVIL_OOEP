package com.vinilo.data.repository

import com.vinilo.domain.model.User

class UserRepository {

    fun getUser(): User {
        // Simula una llamada a base de datos o API
        return User(id = 1, name = "Carlos", email = "carlos@email.com")
    }
}
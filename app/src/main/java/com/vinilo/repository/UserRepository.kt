package com.vinilo.repository

import com.vinilo.model.User

class UserRepository {

    fun getUser(): User {
        // Simula una llamada a base de datos o API
        return User(id = 1, name = "Carlos", email = "carlos@email.com")
    }
}
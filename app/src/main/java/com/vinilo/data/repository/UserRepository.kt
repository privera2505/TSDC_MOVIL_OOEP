package com.vinilo.data.repository

import com.vinilo.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor() {

    fun getUser(): User {
        // Simula una llamada a base de datos o API
        return User(id = 1, name = "Carlos", email = "carlos@email.com")
    }
}
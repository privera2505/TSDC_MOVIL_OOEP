package com.vinilo.enums

import com.google.gson.annotations.SerializedName

enum class Genre(val displayName: String) {
    @SerializedName("Classical")
    CLASSICAL("Clásica"),

    @SerializedName("Salsa")
    SALSA("Salsa"),

    @SerializedName("Rock")
    ROCK("Rock"),

    @SerializedName("Folk")
    FOLK("Folk");

    override fun toString(): String = displayName
}
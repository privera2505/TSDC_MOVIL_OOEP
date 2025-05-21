package com.vinilo.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vinilo.domain.model.CollectorAlbum
import com.vinilo.domain.model.Comment
import com.vinilo.domain.model.Performer
import java.util.Date

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    // Conversores para List<Comment>
    @TypeConverter
    fun fromCommentList(value: List<Comment>): String = gson.toJson(value)

    @TypeConverter
    fun toCommentList(value: String): List<Comment> {
        val listType = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(value, listType)
    }

    // Conversores para List<Performer>
    @TypeConverter
    fun fromPerformerList(value: List<Performer>): String = gson.toJson(value)

    @TypeConverter
    fun toPerformerList(value: String): List<Performer> {
        val listType = object : TypeToken<List<Performer>>() {}.type
        return gson.fromJson(value, listType)
    }

    // Conversores para List<CollectorAlbum>
    @TypeConverter
    fun fromCollectorAlbumList(value: List<CollectorAlbum>): String = gson.toJson(value)

    @TypeConverter
    fun toCollectorAlbumList(value: String): List<CollectorAlbum> {
        val listType = object : TypeToken<List<CollectorAlbum>>() {}.type
        return gson.fromJson(value, listType)
    }
}
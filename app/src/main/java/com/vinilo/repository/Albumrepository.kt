package com.vinilo.repository

import com.vinilo.enums.Genre
import com.vinilo.enums.RecordLabel
import com.vinilo.model.Album
import com.vinilo.model.Comment
import com.vinilo.model.Performer
import com.vinilo.model.Track
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Albumrepository {
    fun getAlbums(): List<Album>{
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val performers = listOf(
            Performer(2, "Adele", "https://example.com/adele.jpg", "Cantautora británica ganadora de múltiples premios Grammy.", formatter.parse("1990-04-10")),
            Performer(2, "Adele", "https://example.com/adele.jpg", "Cantautora británica ganadora de múltiples premios Grammy.", formatter.parse("1990-04-10"))
        )

        val tracks = listOf(
            Track(1, "Track 1", "3:30"),
            Track(2, "Track 2", "4:15")
        )

        val comments = listOf(
            Comment(1, "Excelente álbum"),
            Comment(2, "Me encantó la producción")
        )

        val calendar = Calendar.getInstance()
        calendar.set(2023, Calendar.JUNE, 10)

        return listOf(Album(
            1,
            "Album de Rock",
            "Mi primer Album",
            calendar.time,
            "Description of Rock",
            Genre.ROCK,
            RecordLabel.SONY,
            performers,
            tracks,
            comments
            )
        )
    }
}
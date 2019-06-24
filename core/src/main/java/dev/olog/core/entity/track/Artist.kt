package dev.olog.core.entity.track

import dev.olog.core.MediaId
import dev.olog.core.MediaIdCategory

data class Artist(
        val id: Long,
        val name: String,
        val albumArtist: String,
        val songs: Int,
        val albums: Int,
        val isPodcast: Boolean
)

fun Artist.getMediaId(): MediaId {
    val category = if (isPodcast) MediaIdCategory.PODCASTS_ARTISTS else MediaIdCategory.ARTISTS
    return MediaId.createCategoryValue(category, this.id.toString())
}
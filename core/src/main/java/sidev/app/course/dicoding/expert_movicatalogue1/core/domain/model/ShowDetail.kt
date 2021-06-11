package sidev.app.course.dicoding.expert_movicatalogue1.core.domain.model

import java.io.Serializable

/**
 * For both TV Show and Movie because they have same structure.
 */
data class ShowDetail(
    val show: Show,
    val genres: List<String>,
    val duration: Int?, //in minutes, null if the show type is tv show
    val tagline: String,
    val overview: String,
    val backdropImg: String,
): Serializable
package sidev.app.course.dicoding.expert_movicatalogue1.core.domain.model

import java.io.Serializable


/**
 * For both TV Show and Movie because they have same structure.
 */
data class Show(
    val id: Int,
    val title: String,
    val img: String,
    val release: String,
    val rating: Double,
): Serializable
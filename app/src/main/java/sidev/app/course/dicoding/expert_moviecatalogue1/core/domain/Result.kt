package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain

sealed class Result<out T> {
    abstract val code: Int
}

data class Success<T>(val data: T, override val code: Int): Result<T>()
data class Fail(val msg: String, override val code: Int, val e: Throwable?): Result<Nothing>()
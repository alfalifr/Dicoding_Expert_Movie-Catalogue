package sidev.app.course.dicoding.expert_movicatalogue1.ui

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

fun aga() = flow {
    emit(1)
}

suspend fun agaa() {
    aga().collect {  }
}
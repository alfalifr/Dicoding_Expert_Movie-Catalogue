package sidev.app.course.dicoding.expert_movicatalogue1.core.util

import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.Fail

object Util {
    fun fail(msg: String = "Fail", code: Int = -1, e: Throwable? = null): Fail = Fail(msg, code, e)
    fun canGetFail(msg: String = "Can't get value", code: Int = -1, e: Throwable? = null): Fail = fail(msg, code, e)
}
package sidev.app.course.dicoding.expert_moviecatalogue1.core.util

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import sidev.app.course.dicoding.expert_moviecatalogue1.R
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail

object AppUtil {
    fun Context.getDurationString(showDetail: ShowDetail): String? {
        val dur = showDetail.duration ?: return null
        val hour = dur / 60
        val minute = dur % 60
        return getString(R.string.duration, hour, minute)
    }

    // From stackoverflow
    @ColorInt
    fun Context.themeColor(@AttrRes attrRes: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute (attrRes, typedValue, true)
        return typedValue.data
    }
}
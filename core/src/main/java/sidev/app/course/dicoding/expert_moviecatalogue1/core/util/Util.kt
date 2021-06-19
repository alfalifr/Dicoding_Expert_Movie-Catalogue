package sidev.app.course.dicoding.expert_moviecatalogue1.core.util

import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import java.text.SimpleDateFormat
import java.util.*

object Util {

    fun BottomNavigationView.setupWithViewPager(vp: ViewPager2, idMapper: (id: Int) -> Int){
        var isInternallyChanged = true
        setOnNavigationItemSelectedListener {
            isInternallyChanged = false
            vp.currentItem = idMapper(it.itemId)
            isInternallyChanged = true
            true
        }
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(isInternallyChanged){ //So it won't cause an infinite loop.
                    this@setupWithViewPager.selectedItemId = menu[position].itemId
                }
            }
        })
    }


    fun formatDate(dateString: String): String {
        val dates = dateString.split("-")
        val cal = Calendar.getInstance()
        cal.set(dates[0].toInt(), dates[1].toInt(), dates[2].toInt())
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
        return sdf.format(cal.time)
    }

    fun Show.imgUrl_300x450(): String? = if(img == null) null else Const.getImgUrl_300x450(img)
    fun Show.getFormattedDate(): String? = if(release == null) null else formatDate(release)
    fun ShowDetail.backdropImgUrl_533x300(): String? = if(backdropImg == null) null else Const.getImgUrl_533x300(backdropImg)
}
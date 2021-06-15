package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import sidev.app.course.dicoding.expert_moviecatalogue1.R
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity.ShowListAbsActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.fragment.ShowFavListFragment

class ShowFavListActivity: ShowListAbsActivity() {
    override fun createFragment(pos: Int): Fragment = ShowFavListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.show_fav_list)
    }
}
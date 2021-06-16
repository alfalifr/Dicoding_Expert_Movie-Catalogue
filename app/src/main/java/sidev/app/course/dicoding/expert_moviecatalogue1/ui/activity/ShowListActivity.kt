package sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import sidev.app.course.dicoding.expert_moviecatalogue1.R
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment.ShowListFragment
import sidev.lib.android.std.tool.util.`fun`.startAct
import sidev.lib.android.std.tool.util.`fun`.toast

class ShowListActivity: ShowListAbsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.show_list)
    }

    override fun createFragment(pos: Int): Fragment = ShowListFragment()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.menu_fav -> {
            val sm = SplitInstallManagerFactory.create(this)
            if(Const.MODULE_FAV in sm.installedModules) {
                toFavAct()
            } else {
                val req = SplitInstallRequest.newBuilder()
                    .addModule(Const.MODULE_FAV)
                    .build()

                sm.startInstall(req)
                    .addOnSuccessListener {
                        toast(getString(R.string.success_install_favorite))
                        toFavAct()
                    }
                    .addOnFailureListener { toast(getString(R.string.error_install_favorite)) }
            }
            true
        }
        R.id.menu_search -> {
            startAct<SearchActivity>()
            true
        }
        else -> false
    }

    private fun toFavAct(){
        startActivity(
            Intent(this, Class.forName(Const.ACT_FAV_LIST))
        )
    }
}
package sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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
            toOrInstallFeature(
                Const.MODULE_FAV,
                onFailure = { toast(getString(R.string.error_install_favorite)) },
                onSuccess = { toast(getString(R.string.success_install_favorite)) },
            ) {
                toFavAct()
            }
            true
        }
        R.id.menu_search -> {
            toOrInstallFeature(
                Const.MODULE_SEARCH,
                onFailure = { toast(getString(R.string.error_install_search)) },
                onSuccess = { toast(getString(R.string.success_install_search)) },
            ) {
                toSearchAct()
            }
            true
        }
        else -> false
    }

    private fun toFeatureAct(actClassName: String){
        startActivity(
            Intent(this, Class.forName(actClassName))
        )
    }

    private fun toFavAct() = toFeatureAct(Const.ACT_FAV_LIST)
    private fun toSearchAct() = toFeatureAct(Const.ACT_SEARCH)
    private fun toOrInstallFeature(
        moduleName: String,
        onSuccess: (Int) -> Unit = { toast(getString(R.string.success_install_module)) },
        onFailure: (Exception) -> Unit = { toast(getString(R.string.error_install_module)) },
        toFeaturePage: () -> Unit,
    ) {
        val sm = SplitInstallManagerFactory.create(this)
        if(moduleName in sm.installedModules) {
            toFeaturePage()
        } else {
            val req = SplitInstallRequest.newBuilder()
                .addModule(moduleName)
                .build()

            sm.startInstall(req)
                .addOnSuccessListener {
                    onSuccess(it)
                    toFeaturePage()
                }
                .addOnFailureListener(onFailure)
        }
    }
}
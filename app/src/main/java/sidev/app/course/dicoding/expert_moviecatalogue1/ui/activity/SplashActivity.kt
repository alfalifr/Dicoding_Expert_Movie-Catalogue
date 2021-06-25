package sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.R

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash)

        lifecycleScope.launch {
            delay(2000)
            startActivity<ShowListActivity>()
            finish()
        }
    }
}
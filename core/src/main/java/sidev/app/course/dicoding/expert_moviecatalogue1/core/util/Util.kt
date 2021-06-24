package sidev.app.course.dicoding.expert_moviecatalogue1.core.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.core.view.get
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.viewpager2.widget.ViewPager2
import com.github.javafaker.Faker
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.securepreferences.SecurePreferences
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

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

    fun getSharedPref(ctx: Context): SharedPreferences =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val spec = KeyGenParameterSpec.Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
            val masterKey = MasterKey.Builder(ctx)
                .setKeyGenParameterSpec(spec)
                .build()
            EncryptedSharedPreferences
                .create(
                    ctx,
                    Const.PREF_FILE_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
        } else {
            SecurePreferences(ctx, "pswd_ay", Const.PREF_FILE_NAME)
        }

    fun getRandomString(charNumber: Int? = null): String {
        val len = charNumber ?: run {
            Random.nextInt(25)
        }
        return Faker().lorem().fixedString(len)
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
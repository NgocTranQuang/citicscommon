package citics.sharing.activity.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import citics.sharing.dialog.NormalDialog
import citics.sharing.session.CiticsBuildConfig
import citics.sharing.utils.KEY_ANDROID_LATEST_VERSION
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.sharing.R

open class AnalyticActivity : AppCompatActivity() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()
    private val configSettings = FirebaseRemoteConfigSettings.Builder()
        .setMinimumFetchIntervalInSeconds(3600)
        .build()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        firebaseInit()
    }

    private fun firebaseInit() {
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
        }
    }

    override fun onResume() {
        super.onResume()
        checkLatestVersion()
    }

    private fun checkLatestVersion() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    onHandleFirebaseConfig()
                } else {

                }
            }
    }

    private fun onHandleFirebaseConfig() {
        val latestVersion = remoteConfig.getString(KEY_ANDROID_LATEST_VERSION).toIntOrNull() ?: 0
        if (CiticsBuildConfig.VERSION_CODE < latestVersion) {
            NormalDialog.Builder(this).setImage(R.drawable.ic_new_version)
                .setTitle(R.string.title_force_update_new_version)
                .setMessage(R.string.des_force_update_new_version)
                .setPositiveButton(R.string.cap_nhat_ung_dung) { dialog, int ->
                    dialog.dismiss()
                    goToStore()
                    finishAffinity()
                }.show(supportFragmentManager)
        }
    }

    private fun goToStore() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        } catch (anfe: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }
    }
}
package citics.sharing.activity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

/**
 * Created by ChinhQT on 27/10/2022.
 */
open class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // https://developer.android.com/develop/ui/views/layout/insets
        // Lay out your app within window insets
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
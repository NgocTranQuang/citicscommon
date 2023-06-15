package citics.sharing.application

import android.app.Application
import citics.sharing.session.CurrentSession
import citics.sharing.session.AppConstants
import kotlin.reflect.KClass

abstract class BaseApplication<T : AppConstants>() : Application() {
    override fun onCreate() {
        super.onCreate()
        CurrentSession.instance.constants = getAppConstant().java.newInstance()
    }

    abstract fun getAppConstant(): KClass<T>
}
package citics.sharing.session

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import citics.sharing.application.BaseApplication
import com.google.gson.GsonBuilder

class CurrentSession {
    companion object {
        val instance by lazy { CurrentSession() }
    }

    lateinit var constants: AppConstants

//    var token: String
//        get() = PreferenceUtils.getPref(ExtraKey.TOKEN, "", currentActivity)
//        set(value) {
//            if (value.isEmpty()) {
//                PreferenceUtils.remove(ExtraKey.TOKEN, currentActivity)
//                PreferenceUtils.remove(ExtraKey.TOKEN_LAST_TIME, currentActivity)
//            } else {
//                PreferenceUtils.savePref(ExtraKey.TOKEN, value, currentActivity)
//                PreferenceUtils.savePref(ExtraKey.TOKEN_LAST_TIME, System.currentTimeMillis(), currentActivity)
//            }
//        }
//
//    var currentActivity: BaseActivity? = null
//
//    var locationService: LocationService? = null
//
//    var fetchedLatestVersionTime by PersistentData(ExtraKey.FETCHED_LATEST_VERSION, Long::class, context = currentActivity)
//
//    var shouldUpdateLocation by PersistentData(ExtraKey.SHOULD_UPDATE_LOCATION, Boolean::class, context = currentActivity)
//
//    var willLoadMultiAccount by PersistentData(ExtraKey.WILL_LOAD_MULTI_ACC, Int::class, context = currentActivity)
//
//    var lastKnownLocation by LocationObjectDelegate()
//
//    val commonGson by lazy {
//        GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()
//    }
//
//    // To Declare if user is taking image
//    var isImageActivity: Boolean
//        get() = PreferenceUtils.getPref(ExtraKey.IS_IMAGE_ACTIVITY, false)
//        set(value) = PreferenceUtils.savePref(ExtraKey.IS_IMAGE_ACTIVITY, value)
//
//
//    val isLogined: Boolean
//        get() = token.isNotEmpty()
//
//    fun startLocation() {
//        (currentActivity?.application as? BaseApplication<*>)?.run {
//            if (locationService == null && !isServiceRunning(LocationService::class)) {
//                with(Intent(this, LocationService::class.java)) intent@{
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        startForegroundService(this)
//                    } else {
//                        startService(this)
//                    }
//                    with(object : ServiceConnection {
//                        override fun onServiceDisconnected(service: ComponentName?) {
//
//                        }
//
//                        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//                            if (locationService == null) {
//                                locationService = (service as? LocationService.MyBinder)?.service?.apply {
//                                    startLocationUpdates()
//                                }
//                            }
//                        }
//                    }) {
//                        serviceLocation = this
//                        bindService(this@intent, this, Context.BIND_AUTO_CREATE)
//                    }
//                }
//            }
//        }
//    }
//
//    fun stopLocation() {
//        (currentActivity?.application as? BaseApplication<*>)?.run {
//            serviceLocation?.run { unbindService(this) }
//            serviceLocation = null
//            locationService?.stopSelf()
//            locationService = null
//        }
//    }
}
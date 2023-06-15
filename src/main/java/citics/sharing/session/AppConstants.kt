package citics.sharing.session

import okhttp3.Interceptor
import okhttp3.Response

abstract class AppConstants {

    abstract val FULL_END_POINT_LOGIN_NOMAL: String
    abstract val FULL_END_POINT_LOGIN_BIOMETRIC: String
    abstract val BASE_URL_API_MAIN: String
    abstract val BASE_URL_API_UPLOADER: String
    abstract val BASE_URL_API_SEARCH: String
    abstract val VERSION_CODE: Int
    abstract val APPLICATION_ID: String
    abstract val PUBLIC_KEY_LOGIN_NOMAL: String
    abstract val PUBLIC_KEY_LOGIN_BIOMETRIC: String
//
//    abstract val GOOGLE_DIRECTION_API_KEY: String
//    abstract val SERVER_API_KEY: String
//    abstract val STARTUP_SCREEN: String
//    abstract val MESSAGE_ERROR_DEFAULT: String
//
//
//    val ACTION_LOCATION = "TOM_ACTION_LOCATION"
//
//    val ANIMATION_TIME_MAP = 2000
//
//
//    open val FAKE_USER: ((chain: Interceptor.Chain) -> Response)?
//        get() = null
//
//    open val SUPPORTED_IMAGE_TYPE: Array<String>
//        get() = arrayOf("jpg", "jpeg", "png")
//
//    open val MAX_BITMAP_SIZE: Int
//        get() = 2000
//
//    open var LOADING_ICON_COLOR: Int? = 0xffff0000.toInt()
//
//    open var CUSTOM_DEFAULT_FONT_NAME: String? = null
//
//    open var API_CONNECTION_TIME_OUT: Long = 60
//
//    open var API_READ_TIMEOUT: Long = 60
//
//    open var API_WRITE_TIMEOUT: Long = 60
//
//    open var MINIMUM_MINUTES_CHECK_VERSION_LATEST: Int = 30
//
//    open var API_SERVER_DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss"
}
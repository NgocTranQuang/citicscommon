package citics.sharing.service.header

import android.os.Build
import android.util.Log
import com.citics.valuation.data.datasource.local.PreferenceManager
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by ChinhQT on 01/10/2022.
 */
@Singleton
class ApiHeadersProvider @Inject constructor(private val preferenceManager: PreferenceManager) {

    /**
     * Public headers for calls that do not need an authenticated user.
     */
    fun getPublicHeaders(): PublicHeaders = PublicHeaders().apply {
        putAll(getDefaultHeaders())
    }

    /**
     * Returns both the default headers and the headers that are mandatory for authenticated users.
     */
    fun getAuthenticatedHeaders(): AuthenticatedHeaders = AuthenticatedHeaders().apply {
        putAll(getDefaultHeaders())
        put(AUTHORIZATION, getBearer(preferenceManager.jwtToken))
    }

    /**
     * Default headers used on all calls.
     */
    private fun getDefaultHeaders() = mapOf(
        ACCEPT_LANGUAGE to preferenceManager.language,
        HEADER_ACCEPT to "application/json",
        USER_AGENT to userAgent,
        DEVICE_NAME to getDeviceName(),
        CTC_PROVIDER to "bank",
        DEVICE_ID to preferenceManager.deviceID
    )

    val userAgent = System.getProperty("http.agent")


    companion object {
        private const val ACCEPT_LANGUAGE = "Accept-Language"
        private const val USER_AGENT = "User-Agent"
        private const val AUTHORIZATION = "Authorization"
        private const val HEADER_ACCEPT = "Accept"
        private const val CONTENT_TYPE = "Content-Type"
        private const val DEVICE_NAME = "Device-Name"
        private const val DEVICE_ID = "Device-ID"
        private const val CTC_PROVIDER = "CTC-Provider"

        private fun getBearer(jwtToken: String) = "Bearer $jwtToken"
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        val deviceName = if (model.lowercase(Locale.getDefault())
                .startsWith(manufacturer.lowercase(Locale.getDefault()))
        ) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
        Log.d("getDeviceName", "getDeviceName: ${deviceName}")
        return deviceName
    }

    private fun capitalize(s: String?): String {
        if (s == null || s.length == 0) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            first.uppercaseChar().toString() + s.substring(1)
        }
    }


}

open class ApiMainHeaders : HashMap<String, String>()
class AuthenticatedHeaders : ApiMainHeaders()
class PublicHeaders : ApiMainHeaders()
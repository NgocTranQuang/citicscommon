package citics.sharing.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.icu.text.NumberFormat
import citics.sharing.data.model.response.UserResponse
import citics.sharing.data.model.response.tham_dinh.RecordResponse
import citics.sharing.data.datasource.local.PreferenceManager
import citics.sharing.extension.getDrawableFromName
import java.util.*


/**
 * Created by ChinhQT on 25/10/2022.
 */
object Utils {

    fun saveUserAccount(
        preferenceManager: PreferenceManager,
        username: String,
        user: UserResponse.Data
    ) {
        preferenceManager.userId = user.user_id
        preferenceManager.userId = user.user_id
        preferenceManager.jwtToken = user.token
        preferenceManager.user_login = username
        preferenceManager.isLogined = true
    }

    fun saveJWTToken(preferenceManager: PreferenceManager, jwtToken: String) {
        if (jwtToken.isNotEmpty()) {
            preferenceManager.jwtToken = jwtToken
        }
    }

    fun saveFingerID(preferenceManager: PreferenceManager, fingerID: String) {
        preferenceManager.fingerID = fingerID
    }

    fun resetUserAccount(preferenceManager: PreferenceManager) {
        preferenceManager.jwtToken = ""
        preferenceManager.userId = ""
        preferenceManager.isLogined = false
        preferenceManager.userName = ""
        preferenceManager.user_login = ""
        preferenceManager.fingerID = ""
    }

    fun formatNumberWithoutUnit(number: String): String {
        return if (number.isNotEmpty()) {
            val temporary = number.toDouble()
            NumberFormat.getNumberInstance(Locale.US).format(temporary)
        } else {
            ""
        }
    }

    fun formatNumberWithoutUnit(number: Double?): String {
        return if (number != null) {
            NumberFormat.getNumberInstance(Locale.US).format(number)
        } else {
            ""
        }
    }

    fun formatNumberWithoutUnit(number: Float?): String {
        return if (number != null) {
            NumberFormat.getNumberInstance(Locale.US).format(number)
        } else {
            ""
        }
    }

    fun formatNumberWithoutUnit(number: Long?): String {
        return if (number != null) {
            NumberFormat.getNumberInstance(Locale.US).format(number)
        } else {
            ""
        }
    }

    fun isCountDown(executionTime: RecordResponse.RecordData.ExecutionTime?): Boolean {
        executionTime?.let {
            it.finishedDate?.let {
                // Don't Timer
                return false
            } ?: kotlin.run {
                val currentTime = System.currentTimeMillis()

                it.startedDate?.let { startedDate ->
                    if (startedDate > currentTime) {
                        // Don't Timer
                        return false
                    } else {
                        it.timeInWorks.forEach { timeInWorks ->
                            val start = timeInWorks.start
                            val end = timeInWorks.end

                            if (start != null && end != null) {
                                if (currentTime in start..end) {
                                    return true
                                }
                            }

                        }
                    }
                }
            }
        }

        return false
    }

    fun getDrawableMDSDDByName(context: Context?, name: String?): Drawable? {
        return context?.getDrawableFromName("ic_mdsdd_" + name?.lowercase())
    }
}
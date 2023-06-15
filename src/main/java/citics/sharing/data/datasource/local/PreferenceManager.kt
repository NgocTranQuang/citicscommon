package com.citics.valuation.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import citics.sharing.utils.Language
import com.citics.cbank.BuildConfig
import javax.inject.Inject

/**
 * Created by ChinhQT on 25/09/2022.
 */
class PreferenceManager @Inject constructor(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = BuildConfig.APPLICATION_ID
        private const val PREF_KEY_IS_LOGINED = "PREF_KEY_IS_LOGINED"
        private const val PREF_KEY_JWT_TOKEN = "PREF_KEY_JWT_TOKEN"
        private const val PREF_KEY_FINGER_ID = "PREF_KEY_FINGER_ID"
        private const val PREF_KEY_SHOW_POPUP_LOGIN_WITH_FINGERID =
            "PREF_KEY_SHOW_POPUP_LOGIN_WITH_FINGERID"
        private const val PREF_KEY_USER_ID = "PREF_KEY_USER_ID"
        private const val PREF_KEY_LANGUAGE = "PREF_KEY_LANGUAGE"
        private const val PREF_KEY_DEVICE_ID = "PREF_KEY_DEVICE_ID"
        private const val PREF_KEY_USER_AGENT = "PREF_KEY_USER_AGENT"
        private const val PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME"
        private const val PREF_KEY_TIEPNHAN_HOSO = "PREF_KEY_TIEPNHAN_HOSO"
        private const val PREF_KEY_TIEPNHAN_HOSO_END_DATE = "PREF_KEY_TIEPNHAN_HOSO_END_DATE"
        private const val PREF_KEY_PDF_DOWNLOAD = "PREF_KEY_PDF_DOWNLOAD"
        private const val PREF_KEY_AVARTAR = "PREF_KEY_AVARTAR"
        private const val PREF_KEY_EMAIL = "PREF_KEY_EMAIL"
        private const val PREF_KEY_USER_LOGIN = "PREF_KEY_EMAIL_LOGIN"
        private const val PREF_KEY_SO_DIEN_THOAI = "PREF_KEY_SO_DIEN_THOAI"
        private const val PREF_KEY_CHUC_DANH = "PREF_KEY_CHUC_DANH"
        private const val PREF_KEY_NGAY_SINH = "PREF_KEY_NGAY_SINH"
        private const val PREF_KEY_GIOI_TINH = "PREF_KEY_GIOI_TINH"
        private const val PREF_KEY_CMND = "PREF_KEY_CMND"
        private const val PREF_KEY_DCLH = "PREF_KEY_DCLH"
        private const val PREF_KEY_CAP_DO_CV = "PREF_KEY_CAP_DO_CV"
        private const val PREF_KEY_NLV = "PREF_KEY_NLV"
        private const val PREF_KEY_CDXLHS = "PREF_KEY_CDXLHS"
        private const val PREF_NUMBER_HO_SO = "PREF_NUMBER_HO_SO"
        private const val PREF_LATITUDE = "PREF_LATITUDE"
        private const val PREF_LONGTITUDE = "PREF_LONGTITUDE"
        private const val PREF_NUMBER_VALIDATION = "PREF_NUMBER_VALIDATION"
        private const val PREF_KEY_IGNORE_LOOUP_INSTRUCTION_DIALOG =
            "PREF_KEY_IGNORE_LOOUP_INSTRUCTION_DIALOG"
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var isLogined: Boolean
        get() = pref.getBoolean(PREF_KEY_IS_LOGINED, false)
        set(value) = pref.edit().putBoolean(PREF_KEY_IS_LOGINED, value).apply()

    var jwtToken: String
        get() = pref.getString(
            PREF_KEY_JWT_TOKEN, ""
        ) ?: ""
        set(value) = pref.edit().putString(PREF_KEY_JWT_TOKEN, value).apply()


    var fingerID: String
        get() = pref.getString(
            PREF_KEY_FINGER_ID, ""
        ) ?: ""
        set(value) = pref.edit().putString(PREF_KEY_FINGER_ID, value).apply()

    var showPopupLoginWithFingerID: Boolean
        get() = pref.getBoolean(
            PREF_KEY_SHOW_POPUP_LOGIN_WITH_FINGERID, false
        )
        set(value) = pref.edit().putBoolean(PREF_KEY_SHOW_POPUP_LOGIN_WITH_FINGERID, value).apply()

    var userId: String
        get() = pref.getString(
            PREF_KEY_USER_ID, ""
        ) ?: ""
        set(value) = pref.edit().putString(PREF_KEY_USER_ID, value).apply()

    var language: String
        get() = pref.getString(PREF_KEY_LANGUAGE, Language.VIET_NAM.language)
            ?: Language.VIET_NAM.language
        set(value) = pref.edit().putString(PREF_KEY_LANGUAGE, value).apply()

    var deviceID: String
        get() = pref.getString(PREF_KEY_DEVICE_ID, "")
            ?: ""
        set(value) = pref.edit().putString(PREF_KEY_DEVICE_ID, value).apply()

    var userAgent: String
        get() = pref.getString(PREF_KEY_USER_AGENT, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_USER_AGENT, value).apply()

    var numberHoSo: Int
        get() = pref.getInt(PREF_NUMBER_HO_SO, 0)
        set(value) = pref.edit().putInt(PREF_NUMBER_HO_SO, value).apply()

    var latitude: Float
        get() = pref.getFloat(PREF_LATITUDE, 0F)
        set(value) = pref.edit().putFloat(PREF_LATITUDE, value).apply()

    var longtitude: Float
        get() = pref.getFloat(PREF_LONGTITUDE, 0F)
        set(value) = pref.edit().putFloat(PREF_LONGTITUDE, value).apply()

    var validationTab: Int
        get() = pref.getInt(PREF_NUMBER_VALIDATION, 0)
        set(value) = pref.edit().putInt(PREF_NUMBER_VALIDATION, value).apply()

    var userName: String
        get() = pref.getString(PREF_KEY_USER_NAME, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_USER_NAME, value).apply()

    var avatar: String
        get() = pref.getString(PREF_KEY_AVARTAR, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_AVARTAR, value).apply()

    var email: String
        get() = pref.getString(PREF_KEY_EMAIL, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_EMAIL, value).apply()

    var user_login: String
        get() = pref.getString(PREF_KEY_USER_LOGIN, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_USER_LOGIN, value).apply()

    var sodienthoai: String
        get() = pref.getString(PREF_KEY_SO_DIEN_THOAI, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_SO_DIEN_THOAI, value).apply()

    var chucdanh: String
        get() = pref.getString(PREF_KEY_CHUC_DANH, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_CHUC_DANH, value).apply()

    var capdochuyenvien: String
        get() = pref.getString(PREF_KEY_CAP_DO_CV, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_CAP_DO_CV, value).apply()

    var nhomlamviec: String
        get() = pref.getString(PREF_KEY_NLV, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_NLV, value).apply()

    var cdxlhs: String
        get() = pref.getString(PREF_KEY_CDXLHS, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_CDXLHS, value).apply()

    var ngaysinh: String
        get() = pref.getString(PREF_KEY_NGAY_SINH, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_NGAY_SINH, value).apply()

    var gioitinh: String
        get() = pref.getString(PREF_KEY_GIOI_TINH, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_GIOI_TINH, value).apply()

    var cmnd: String
        get() = pref.getString(PREF_KEY_CMND, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_CMND, value).apply()

    var diachilienhe: String
        get() = pref.getString(PREF_KEY_DCLH, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_DCLH, value).apply()

    var pdfDownload: String
        get() = pref.getString(PREF_KEY_PDF_DOWNLOAD, "") ?: ""
        set(value) = pref.edit().putString(PREF_KEY_PDF_DOWNLOAD, value).apply()

    var isTiepNhanHoSo: Boolean
        get() = pref.getBoolean(PREF_KEY_TIEPNHAN_HOSO, false) ?: false
        set(value) = pref.edit().putBoolean(PREF_KEY_TIEPNHAN_HOSO, value).apply()

    var on_receive_record_date: Long
        get() = pref.getLong(PREF_KEY_TIEPNHAN_HOSO_END_DATE, 0L)
        set(value) = pref.edit().putLong(PREF_KEY_TIEPNHAN_HOSO_END_DATE, value).apply()

    var shouldIgnoreLookupInstructionDialog: Boolean
        get() = pref.getBoolean(
            PREF_KEY_IGNORE_LOOUP_INSTRUCTION_DIALOG, false
        )
        set(value) = pref.edit().putBoolean(PREF_KEY_IGNORE_LOOUP_INSTRUCTION_DIALOG, value).apply()

    fun clearAll(){
        pref.edit().clear().commit()
    }
}
package citics.sharing.utils

import citics.sharing.data.model.others.ChooserItem


/**
 * Created by ChinhQT on 25/09/2022.
 */

const val DATABASE_NAME = "CiticsDB"

//region SERVER_CODE
const val SERVER_CODE_INVALID_USER = 401
const val SERVER_CODE_INVALID_PASSWORD = 402
const val SERVER_CODE_INVALID_C_POINT_408 = 408
const val SERVER_CODE_INVALID_C_POINT_409 = 409
const val SERVER_CODE_LOGIN_IN_NEW_DEVICE = 413
//endregion

//region CLIENT_CODE
const val CLIENT_CODE_NETWORK_ERROR = 1
const val CLIENT_CODE_UNKNOWN_ERROR = 2

// ACCOUNT_LOCK_CODE

const val LOCK_TEMP = 411
const val LOCK_PERMANENT = 412

//endregion

const val LTS_NHA_PHO = "nha_pho"
const val LTS_CAN_HO = "can_ho"
const val LTS_TAI_SAN_CHINH = "LTS_TAI_SAN_CHINH"
const val LTS_TAI_SAN_SO_SANH = "LTS_TAI_SAN_SO_SANH"

const val RECORD_ID = "RECORD_ID"
const val RECORD_DATA = "RECORD_DATA"
const val ASSET_DATA = "ASSET_DATA"
const val LAND_DATA = "LAND_DATA"
const val LOAI_TAI_SAN = "LOAI_TAI_SAN"

const val PERMISSION_REQUEST_CAMERA = 1000
const val PERMISSION_REQUEST_STORAGE = 1001
const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1002
const val PERMISSION_REQUEST_ALL_FILE_STORAGE = 1003

const val PASSWORD_MAX_LENGTH = 4

const val STARTING_PAGE_INDEX = 0
const val PAGE_SIZE = 10
const val PAGE_SIZE_20 = 20

const val MAP_ZOOM_CITY = 10F
const val MAP_ZOOM_STREETS = 15F
const val MAP_ZOOM_18 = 18F
const val MAP_ZOOM_21 = 21F

const val MAP_POLYGON_STROKE_WIDTH: Float = 2.5f

const val PLACE_TYPE_GOOGLE = "google"
const val PLACE_TYPE_NHA_PHO = "nha_pho"

// Key show UI
const val UNKNOWN = "Unknown"
const val DEFAULT = "-"
const val KEY_KHAC = "Khác"


// format time tham dinh
const val FORMAT_TIME_THAM_DINH = "hh:mm, dd/MM/yyyy"
const val FORMAT_DD_MM_YYYY = "dd/MM/yyyy"

val LOAI_CAN_HO = mutableListOf(
    ChooserItem("Thông thường", "Thông thường"),
    ChooserItem("Officetel", "Officetel"),
    ChooserItem("Sân vườn", "Sân vườn"),
    ChooserItem("Penthouse", "Penthouse"),
    ChooserItem("Dual key", "Dual key"),
    ChooserItem("Duplex", "Duplex"),
    ChooserItem("Sky Villa", "Sky Villa"),
    ChooserItem("Loft", "Loft"),
    ChooserItem("Smartel", "Smartel"),
    ChooserItem("Duplex sân vườn", "Duplex sân vườn"),
    ChooserItem("Triple", "Triple")
)

val LIST_HUONG_NHA = mutableListOf(
    ChooserItem("Đông Nam", "Đông Nam"),
    ChooserItem("Nam", "Nam"),
    ChooserItem("Đông Bắc", "Đông Bắc"),
    ChooserItem("Đông", "Đông"),
    ChooserItem("Bắc", "Bắc"),
    ChooserItem("Tây Nam", "Tây Nam"),
    ChooserItem("Tây Bắc", "Tây Bắc"),
    ChooserItem("Tây", "Tây")
)

const val KEY_ANDROID_LATEST_VERSION = "android_latest_version"
const val MAIL_SUPPORT_CITICS = "hotro@citics.vn"
const val PHONE_SUPPORT_CITICS = "1900633075"

// Choice

const val REQUEST_KEY_SINGLE_CHOICE = "REQUEST_KEY_SINGLE_CHOICE"
const val REQUEST_KEY_SINGLE_CHOICE_KEY = "REQUEST_KEY_SINGLE_CHOICE_KEY"
const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID = "REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID"
const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME = "REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME"
const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAM = "REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME"
const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_NGAY_THANG =
    "REQUEST_KEY_SINGLE_CHOICE_SELECTED_NGAY_THANG"
const val REQUEST_KEY_MULTI_CHOICE = "REQUEST_KEY_MULTI_CHOICE"
const val REQUEST_KEY_MULTI_CHOICE_KEY = "REQUEST_KEY_MULTI_CHOICE_KEY"
const val REQUEST_KEY_MULTI_CHOICE_SELECTED = "REQUEST_KEY_MULTI_CHOICE_SELECTED"

const val TITLE = "title"
const val LST_DATA = "lstData"
const val SELECTED = "selected"
const val LST_SELECTED = "lstSelected"
const val HAS_OTHER = "HAS_OTHER"
const val KEY_MESSAGE_NO_DATA = "KEY_MESSAGE_NO_DATA"

const val KEY_BUNDLE_DATA = "KEY_BUNDLE_DATA"
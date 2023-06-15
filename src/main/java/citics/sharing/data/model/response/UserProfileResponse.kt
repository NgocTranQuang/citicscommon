package citics.sharing.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 24/10/2022.
 */
data class UserProfileResponse(
    val status: Boolean = false, val message: String?, val data: Data? = null
) {
    @Parcelize
    data class Data(
        val id: String = "",
        val email: String = "",
        val mobile: String = "",
        val avatar: Avatar? = null,
        val name: String = "",
        val address: String? = null,
        val activated: Boolean? = null,
        val longitude: Double? = null,
        val latitude: Double? = null,
        val gender: String? = null,
        val card_number: String? = null,
        val off_receive_record: Boolean? = null,
        val on_receive_record_date: Long? = null,
        val notificationCount: String? = "2380",
        val chuc_vu: KeyTitleDTO? = null,
        val cap_do_xu_ly_ho_so_title: String? = null,
        val cap_do_moi_gioi_title: String? = null,
        val ngay_thang_nam_sinh: String? = null,
        val authorities: List<String>? = null,
        val groups_title: List<String>? = null
    ) : Parcelable

    @Parcelize
    data class Avatar(
        val url: String = ""
    ) : Parcelable
}
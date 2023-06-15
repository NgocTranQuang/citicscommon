package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class QhxdDTO(
    @Json(name = "mat_do_xay_dung") var mat_do_xay_dung: String? = null,
    @Json(name = "tang_cao") var tang_cao: String? = null,
    @Json(name = "file_quyet_dinh") var file_quyet_dinh: String? = null,
    @Json(name = "file_quyet_dinh_url") var file_quyet_dinh_url: String? = null
) : Parcelable {
}
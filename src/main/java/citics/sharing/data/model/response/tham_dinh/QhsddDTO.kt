package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class QhsddDTO(
    @Json(name = "thoi_ki_quy_hoach") var thoi_ki_quy_hoach: String? = null,
    @Json(name = "loai_dat") var loai_dat: String? = null,
    @Json(name = "so_quyet_dinh") var so_quyet_dinh: String? = null,
    @Json(name = "quyet_dinh_url") var quyet_dinh_url: String? = null
) : Parcelable {
}
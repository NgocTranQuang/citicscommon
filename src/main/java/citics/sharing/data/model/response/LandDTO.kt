package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class LandDTO(
    @Json(name = "name") val name: String? = null,
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "phuong_thuc_tinh_dat_vpqh") val phuong_thuc_tinh_dat_vpqh: String? = null,
    @Json(name = "dien_tich_dat_vpqh") var dien_tich_dat_vpqh: Float? = null,
    @Json(name = "don_gia_dat_vpqh") val don_gia_dat_vpqh: Double? = null,
    @Json(name = "gia_tri_dat_vpqh") val gia_tri_dat_vpqh: Double? = null,
    @Json(name = "dien_tich_dat_phqh") var dien_tich_dat_phqh: Float? = null,
    @Json(name = "don_gia_dat_phqh") val don_gia_dat_phqh: Double? = null,
    @Json(name = "gia_tri_dat_phqh") val gia_tri_dat_phqh: Double? = null,
    @Json(name = "dien_tich_dat_phqh_nhm") val dien_tich_dat_phqh_nhm: Float? = null,
    @Json(name = "dien_tich_dat_phqh_thm") val dien_tich_dat_phqh_thm: Float? = null,
    @Json(name = "don_gia_dat_phqh_nhm") val don_gia_dat_phqh_nhm: Float? = null,
    @Json(name = "gia_tri_dat") val gia_tri_dat: Float? = null,
    @Json(name = "dien_tich_dat") var dien_tich_dat: Float? = null,
    @Json(name = "ty_le_tinh_dat_vpqh") val ty_le_tinh_dat_vpqh: Float? = null,
    @Json(name = "don_gia_ubnd") var don_gia_ubnd: Long? = null
) : Parcelable
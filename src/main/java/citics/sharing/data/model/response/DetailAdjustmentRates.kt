package citics.sharing.data.model.response

import android.os.Parcelable
import citics.sharing.utils.UNKNOWN
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 08/11/2022.
 */
@Parcelize
data class DetailAdjustmentRates(
    @Json(name = "don_gia") var don_gia: Long? = 0,
    @Json(name = "tong_gia_tri_cong_trinh") val tong_gia_tri_cong_trinh: Long? = 0,
    @Json(name = "bien_do_gia_sai_lech") val bien_do_gia_sai_lech: Float? = 0F,
    @Json(name = "bien_do_gia_thap_nhat") val bien_do_gia_thap_nhat: Long? = 0,
    @Json(name = "gia_tri_tham_dinh") val gia_tri_tham_dinh: Long? = 0,
    @Json(name = "bien_do_gia_cao_nhat") val bien_do_gia_cao_nhat: Long? = 0,
    @Json(name = "tong_gia_tri_dat_tham_dinh") val tong_gia_tri_dat_tham_dinh: Long? = 0,
    @Json(name = "tong_gia_tri_tham_dinh") val tong_gia_tri_tham_dinh: Long? = 0,
    @Json(name = "chi_tiet_don_gia_dat") val chi_tiet_don_gia_dat: List<LandDTO>? = null,
    @Json(name = "chi_tiet_gia_cong_trinh") val chi_tiet_gia_cong_trinh: List<CongTrinh>? = null,
    @Json(name = "my_asset") val my_asset: MyAsset? = null,
    @Json(name = "c_value") val c_value: CValue? = null,
    val lich_su_tham_dinh_gia: Long? = null,
    val khach_hang: String = UNKNOWN,
    val cap_nhat_luc: String = UNKNOWN
) : Parcelable {


    @Parcelize
    data class MyAsset(
        @Json(name = "my_asset_id") val my_asset_id: String,
        @Json(name = "gia_tri_tham_dinh") val gia_tri_tham_dinh: Long? = null
    ) : Parcelable

    @Parcelize
    data class CValue(
        @Json(name = "ngay_cap_nhat_gia") val ngay_cap_nhat_gia: Long? = null,
        @Json(name = "don_gia") var don_gia: Long? = 0,
        @Json(name = "bien_do_gia_thap_nhat") val bien_do_gia_thap_nhat: Long? = 0,
        @Json(name = "gia_tri_tham_dinh") val gia_tri_tham_dinh: Long? = 0,
        @Json(name = "bien_do_gia_cao_nhat") val bien_do_gia_cao_nhat: Long? = 0,
        @Json(name = "tong_gia_tri_cong_trinh") val tong_gia_tri_cong_trinh: Long? = 0,
        @Json(name = "chi_tiet_don_gia_dat") val chi_tiet_don_gia_dat: List<LandDTO>? = null,
    ) : Parcelable
}
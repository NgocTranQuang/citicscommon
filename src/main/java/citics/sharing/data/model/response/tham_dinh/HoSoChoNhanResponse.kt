package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import citics.sharing.data.model.response.PhapLyDTO
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 19/12/2022.
 */
@Parcelize
data class HoSoChoNhanResponse(
    val status: Boolean? = null, val message: String? = null, val data: List<Data>? = null
) : Parcelable {

    @Parcelize
    data class Data(
        var id: String? = null,
        var loai_tai_san: LoaiTaiSan? = null,
        var kinh_do: Double? = null,
        var vi_do: Double? = null,
        var dia_chi1: String? = null,
        var dia_chi2: String? = null,
        var toa_do: String? = null,
        var khoang_cach: String? = null,
        var hoa_hong: Long? = null,
        var khoang_thoi_gian_ket_thuc: Long? = null,
        var nhom_hinh_phap_ly: List<PhapLyDTO>? = null,
        var created_date: Long? = null,
        var survey_time: SurveyTime? = null,
        var thoi_diem_bat_dau: Long? = null,
        var so_luong_tham_gia: Int? = null,
        @Json(name = "tasks") var tasks: List<Task>? = null
    ) : Parcelable

    @Parcelize
    data class LoaiTaiSan(val id: Int? = null, val value: String? = null, val key: String? = null) :
        Parcelable
}
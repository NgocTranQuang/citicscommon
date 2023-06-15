package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EstimationConstructionRequest(
    val loai_cong_trinh: String? = null,
    val so_tang_noi: Int? = null,
    val ket_cau: String? = null,
    val nam_hoan_thanh: Int? = null,
    val nam_sua_chua: Int? = null,
) : Parcelable {
}
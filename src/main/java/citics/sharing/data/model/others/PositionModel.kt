package citics.sharing.data.model.others

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 07/11/2022.
 */
@Parcelize
data class PositionModel(val latitude: Double, val longtitude: Double, val id: String?) :
    Parcelable {
    var dia_chi_1: String? = null
    var dia_chi_2: String? = null
    var loai_tai_san: String? = null
}
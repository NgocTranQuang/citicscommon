package citics.sharing.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class HuongTiepGiapDTO(
    var bac: String? = null,
    var dong: String? = null,
    var dong_bac: String? = null,
    var dong_nam: String? = null,
    var nam: String? = null,
    var tay: String? = null,
    var tay_bac: String? = null,
    var tay_nam: String? = null
) : Parcelable
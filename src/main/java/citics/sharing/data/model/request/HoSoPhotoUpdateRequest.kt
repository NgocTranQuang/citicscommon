package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HoSoPhotoUpdateRequest(
    val id: String? = null,
    val files: List<String>? = null,
    var category: String? = null
) : Parcelable {
}
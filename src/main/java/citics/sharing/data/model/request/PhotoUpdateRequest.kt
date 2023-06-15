package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoUpdateRequest(
    val record_id: String? = null,
    val files: List<String>? = null,
    var photo_tag: String? = null
) : Parcelable {
}
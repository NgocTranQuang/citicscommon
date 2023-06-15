package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeletePhotoRequest(
    val compared_id: String? = null,
    val id: String? = null,
    val legals: List<String>? = null
) : Parcelable {
}
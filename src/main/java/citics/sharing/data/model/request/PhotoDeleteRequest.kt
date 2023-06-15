package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoDeleteRequest(
    val record_id: String? = null,
    val file_ids: List<String>? = null
) : Parcelable {
}
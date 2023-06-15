package citics.sharing.data.model.request.legal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalCreateOrUpdateRequest(
    val id: String? = null,
    val files: List<String>? = null,
    var content: String? = null,
    var tag: String? = null,
    val compared_id: String? = null
) : Parcelable {
}
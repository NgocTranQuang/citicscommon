package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalPhotoUpdateRequest(
    val compared_id: String? = null,
    val id: String? = null,
    val legal: List<String>? = null,
    var legal_content: String? = null,
    var legal_tag: String? = null
) : Parcelable {
}
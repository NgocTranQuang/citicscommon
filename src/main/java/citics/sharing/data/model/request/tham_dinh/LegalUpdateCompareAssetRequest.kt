package citics.sharing.data.model.request.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalUpdateCompareAssetRequest(
    val compared_id: String? = null,
    val legal: List<String>? = null,
    var legal_content: String? = null,
    var legal_tag: String? = null
) : Parcelable {
}
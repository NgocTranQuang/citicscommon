package citics.sharing.data.model.request.legal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalDeleteDocumentRequest(
    val id: String? = null,
    val files: List<String>? = null,
    val compared_id: String? = null
) : Parcelable
package citics.sharing.data.model.request.legal

import android.os.Parcelable
import citics.sharing.data.model.request.PhotoNameUpdateRequest
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalUpdateNameDocumentRequest(
    var id: String? = null,
    var files: MutableList<PhotoNameUpdateRequest>? = null,
    var compared_id: String? = null
    ) : Parcelable

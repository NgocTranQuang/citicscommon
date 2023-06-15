package citics.sharing.data.model.request.tham_dinh

import android.os.Parcelable
import citics.sharing.data.model.request.PhotoNameUpdateRequest
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalTSSSThamDinhPhotoNameUpdateRequest(
    var compared_id: String? = null,
    var legal: MutableList<PhotoNameUpdateRequest>? = null
) : Parcelable

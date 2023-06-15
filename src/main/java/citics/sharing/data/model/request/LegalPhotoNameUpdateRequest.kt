package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalPhotoNameUpdateRequest(
    var compared_id: String? = null,
    var id: String? = null,
    var legal: MutableList<PhotoNameUpdateRequest>? = null
) : Parcelable

@Parcelize
data class PhotoNameUpdateRequest(var file_id: String? = null, var file_name: String? = null) :
    Parcelable
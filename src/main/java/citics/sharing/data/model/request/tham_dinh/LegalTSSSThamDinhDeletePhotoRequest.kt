package citics.sharing.data.model.request.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalTSSSThamDinhDeletePhotoRequest(
    val compared_id: String? = null,
    val legals: List<String>? = null
) : Parcelable
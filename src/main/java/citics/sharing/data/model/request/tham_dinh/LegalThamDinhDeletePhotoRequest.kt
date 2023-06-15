package citics.sharing.data.model.request.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalThamDinhDeletePhotoRequest(
    val record_id: String? = null,
    val legals: List<String>? = null
) : Parcelable
package citics.sharing.data.model.request.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalTSSSThamDinhDeleteRequest(
    val compared_id: String? = null,
    val legal_tag: String? = null
) : Parcelable
package citics.sharing.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalPropertyResponse(
    val legal: List<String>? = null,
    val legal_status: List<String>? = null
) : Parcelable {
}
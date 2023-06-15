package citics.sharing.data.model.request.legal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LegalDeleteTagRequest(
    val id: String? = null,
    val tag: String? = null,
    val compared_id: String?=null
) : Parcelable
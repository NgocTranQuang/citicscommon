package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatusBarResponse(
    val status: Int? = null,
    val title: String? = null,
    val key: String? = null,
    val color: String? = null,
) : Parcelable {
    var number: Int? = null
}
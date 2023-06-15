package citics.sharing.data.model.response

import android.os.Parcelable
import citics.sharing.data.model.response.Document
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhapLyDTO(
    var title: String? = null,
    var content: String? = null,
    var files: List<Document>? = null
) : Parcelable {
    var isAvailable = false
}
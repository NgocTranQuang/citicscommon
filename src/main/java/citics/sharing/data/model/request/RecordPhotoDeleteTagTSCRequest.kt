package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecordPhotoDeleteTagTSCRequest(
    val record_id: String? = null,
    var photo_tag: String? = null
) : Parcelable {
}
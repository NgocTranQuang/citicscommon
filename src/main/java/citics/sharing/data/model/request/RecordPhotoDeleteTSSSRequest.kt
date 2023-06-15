package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecordPhotoDeleteTSSSRequest(
    val compared_id: String? = null,
    var photo_tag: String? = null
) : Parcelable {
}
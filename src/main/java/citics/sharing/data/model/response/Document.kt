package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupPhotos(
    var title: String? = "", var content: String? = "", var files: List<Document>? = null
) : Parcelable {
    @IgnoredOnParcel
    var isAvailable = false

    @IgnoredOnParcel
    var section: String? = null
}

@Parcelize
data class Document(
    @Json(name = "photo_id") val photoID: String? = null,
    @Json(name = "photo_url") val url: String? = null,
    @Json(name = "photo_title") var title: String? = null,
    @Json(name = "photo_thumbnail") var photo_thumbnail: String? = null,
    @Json(name = "photo_tag") var photo_tag: String? = null,
    @Json(name = "content") var content: String? = null,
    @Json(name = "photo_extension") val extension: String? = null,
    @Json(name = "content_type") val contentType: String? = null,
    @Json(name = "photo_size") val size: Double? = null,
    @Json(name = "photo_date") val createdDate: String? = null,
) : Parcelable {
    @IgnoredOnParcel
    var selected: Boolean = false
}
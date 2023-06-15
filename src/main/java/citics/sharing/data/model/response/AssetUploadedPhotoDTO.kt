package citics.sharing.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssetUploadedPhotoDTO(
    val content: String? = null,
    val contentType: String,
    val createdDate: String,
    val extension: String,
    val id: String,
    val tag: String? = null,
    val title: String,
    val url: String,
    val size: Double? = null,
    val priority: Int? = null,
) : Parcelable

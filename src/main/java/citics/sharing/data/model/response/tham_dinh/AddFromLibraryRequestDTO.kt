package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddFromLibraryRequestDTO(
    val record_id: String?=null,
    val photos: List<PhotoInLibraryDTO>?=null,
    val compared_id: String?=null,
    ) : Parcelable


@Parcelize
data class PhotoInLibraryDTO(
    val photo_url: String?,
    val tag_name: String?
) : Parcelable



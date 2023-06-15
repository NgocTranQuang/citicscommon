package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageFromWareHouseResponse(
    val id: String?,
    val name: String?,
    val photos: List<List<PhotosWareHouse>>?,
    val albums: List<ImageFromWareHouseResponse>?
) : Parcelable

@Parcelize
data class PhotosWareHouse(
    val name: String?,
    val value: String?,
    val content_type: String?,
    val photo_size: Long?,
    val photo_extension: String?,
    val photo_title: String?
) : Parcelable

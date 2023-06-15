package citics.sharing.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 28/12/2022.
 */
@Parcelize
data class SliderItemResponse(
    val photo_id: String? = "",
    val photo_url: String? = "",
    val photo_title: String? = "",
    val photo_extension: String? = "",
    val photo_thumbnail: String? = "",
    var content_type: String? = null,
    val photo_size: Int? = 0
) : Parcelable
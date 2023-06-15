package citics.sharing.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 10/11/2022.
 */
@Parcelize
data class CanHoFilterAdvanceResponse(val status: Boolean, val data: Data? = null) : Parcelable {
    @Parcelize
    data class Data(val content: List<ContentItem>? = null) : Parcelable

    @Parcelize
    data class ContentItem(val text: String? = null, val sub_text: String? = null) : Parcelable
}
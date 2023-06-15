package citics.sharing.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class KeyTitleExtraDTO(
    val key: String = "",
    val title: String = "",
    val position : Int? = null
) : Parcelable
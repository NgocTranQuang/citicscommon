package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
class KeyTitleDTO(
    val key: String = "",
    val title: String = ""
) : Parcelable
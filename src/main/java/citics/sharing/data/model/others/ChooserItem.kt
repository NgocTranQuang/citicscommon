package citics.sharing.data.model.others

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 11/11/2022.
 */
@Parcelize
data class ChooserItem(val id: String, var name: String, var isCustomData: Boolean = false) :
    Parcelable {
    var isSelected: Boolean = false
}

@Parcelize
data class SelectorItem(
    val id: String,
    var name: String,
    var isSelected: Boolean = false,
    var isCustomData: Boolean = false
) :Parcelable

@Parcelize
data class SingleChoiceData(
    var title: Int,
    var lstData: List<ChooserItem>?,
    var selected: String?,
    var hasOther: Boolean = false,
    var messageNodata: String? = null
) : Parcelable


@Parcelize
data class MultiChoiceData(
    var title: Int,
    var lstData: List<ChooserItem>?,
    var selected: List<String>?,
    var hasOther: Boolean = false,
    var messageNodata: String? = null
) : Parcelable


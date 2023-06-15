package citics.sharing.data.model.others

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
class DataMainReceivedModel(val tabId: Int, val data: @RawValue Any) : Parcelable {
}
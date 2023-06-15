package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 04/01/2023.
 */
@Parcelize
data class TDLichSuResponse(val status: Boolean? = null, val data: List<Data>? = null) :
    Parcelable {

    @Parcelize
    data class Data(
        val cvalue_record: String? = null,
        val customer: String? = null,
        val number: Long? = null,
        val diff_rate: Float? = null,
        val status: String? = null,
        val price_date: String? = null
    ) : Parcelable
}
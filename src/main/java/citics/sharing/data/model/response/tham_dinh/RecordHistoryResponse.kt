package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 30/01/2023.
 */
@Parcelize
data class RecordHistoryResponse(
    val status: Boolean? = null,
    val data: List<Data>? = null
) : Parcelable {
    @Parcelize
    data class Data(
        val content: String? = null,
        val title: String? = null,
        val user: NoteRecordResponse.User? = null,
        val created_date: Long? = null,
        val ended_date: Long? = null
    ) : Parcelable
}
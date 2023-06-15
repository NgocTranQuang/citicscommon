package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import citics.sharing.data.model.response.Document
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 16/01/2023.
 */
@Parcelize
data class NoteRecordResponse(
    val status: Boolean? = null,
    val message: String? = null,
    val data: Data? = null,
    val pageable: Pageable? = null,
    val totalElements: Int? = 0,
    val totalPages: Int? = 0
) : Parcelable {

    @Parcelize
    data class Data(val content: List<ContentData>? = null) : Parcelable

    @Parcelize
    data class ContentData(
        val content: String? = null,
        val attachment: List<Document>? = null,
        val del: Boolean? = null,
        val id: String? = null,
        val date: Long? = null,
        val user: User? = null,
        val is_del: Boolean? = null
    ) : Parcelable

    @Parcelize
    data class User(
        val user_id: String? = null,
        val name: String? = null,
        val email: String? = null,
        val avatar: String? = null,
        val role: List<Role>? = null,
        val user_type: String? = null,
        val phone_no: String? = null,
        val work_place: String? = null
    ) : Parcelable

    @Parcelize
    data class Role(
        val role: String? = null, val title: String? = null, val priority: Int? = null
    ) : Parcelable
}
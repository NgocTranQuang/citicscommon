package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 19/12/2022.
 */
@Parcelize
data class Task(
    @Json(name = "task_id") val task_id: String,
    @Json(name = "task_action") var task_action: String,
    @Json(name = "allow_editor") val allow_editor: Boolean,
    @Json(name = "task_title") val task_title: String? = null,
    @Json(name = "task_priority") val task_priority: Int,
    @Json(name = "task_assignee") val task_assignee: String? = null,
    @Json(name = "payment_method") val paymentMethod: String? = null
) : Parcelable
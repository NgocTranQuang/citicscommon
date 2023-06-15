package citics.sharing.data.model.request.tham_dinh

import com.google.gson.annotations.SerializedName

/**
 * Created by ChinhQT on 19/12/2022.
 */
data class HoSoRequest(
    val task_id: String?,
    val task_action: String?,
    val id: String?,
    val task_variables: TaskVariables? = null
) {
    data class TaskVariables(
        val reason: String? = null,
        var description: String? = null,
        val attachment: List<String>? = null,
        var is_changed: Boolean? = null,
        var legals: List<String>? = null,
        var survey_time: String? = null,
    )
}
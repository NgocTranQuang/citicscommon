package citics.sharing.extension

import android.app.Activity
import android.content.Intent
import citics.sharing.data.repository.Resource
import com.citics.valuation.data.model.response.ErrorResponse
import com.sharing.R
import kotlinx.coroutines.flow.StateFlow

fun Activity.startActivityWithAnimation(intent: Intent) {
    startActivity(intent)
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.finishWithAnimation() {
    finish()
    overridePendingTransition(
        R.anim.slide_in_left, R.anim.slide_out_right
    )
}

suspend fun <T> StateFlow<Resource<T>>.handleResponseExt(
    onLoading: (() -> Unit)? = null,
    onFail: (suspend ((ErrorResponse?) -> Unit))? = null,
    onSuccess: suspend (T?) -> Unit
) {
    collect {
        when (it) {
            is Resource.Loading -> {
                onLoading?.invoke()
            }
            is Resource.Failure -> {
                onFail?.invoke(it.error)
            }
            is Resource.Success -> {
                onSuccess.invoke(it.data)
            }
            is Resource.None -> {

            }
        }
    }
}

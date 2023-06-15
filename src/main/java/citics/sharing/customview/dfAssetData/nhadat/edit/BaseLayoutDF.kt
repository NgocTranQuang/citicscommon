package citics.sharing.customview.dfAssetData.nhadat.edit

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import citics.sharing.data.model.response.tham_dinh.Properties
import citics.sharing.activity.choice.MultiChoiceActivity
import citics.sharing.data.model.others.MultiChoiceData
import citics.sharing.data.model.others.SelectorItem
import citics.sharing.data.model.others.SingleChoiceData
import citics.sharing.extension.toMutableList
import citics.sharing.utils.*
import com.citics.valuation.ui.activity.choice.SingleChoiceActivity
import com.sharing.R
import timber.log.Timber

open class BaseLayoutDF @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    var properties: Properties? = null
    var startSingleChoiceForResult: ActivityResultLauncher<Intent>? = null
    var startMultiChoiceForResult: ActivityResultLauncher<Intent>? = null

    fun openSingleChoiceActivity(data: SingleChoiceData) {
        startSingleChoiceForResult?.launch(
            SingleChoiceActivity.newIntent(
                context,
                data
            )
        )
    }

    fun openMultiChoiceActivity(data: MultiChoiceData) {
        Timber.d("ID Multichoice openMultiChoiceActivity: ${data.title}")
        startMultiChoiceForResult?.launch(
            MultiChoiceActivity.newIntent(
                context,
                data
            )
        )
    }

    open fun setLauncher(
        singleChoiceLauncher: ActivityResultLauncher<Intent>?,
        multiChoiceLauncher: ActivityResultLauncher<Intent>?
    ) {
        this.startSingleChoiceForResult = singleChoiceLauncher
        this.startMultiChoiceForResult = multiChoiceLauncher
    }

    fun setDataSingleChoiceCallBack(intent: Intent?) {
        intent?.getBundleExtra(KEY_BUNDLE_DATA)?.let { bundle ->

            val title = bundle.getInt(REQUEST_KEY_SINGLE_CHOICE_KEY)
            if (title == R.string.chon_thoi_han) {
                val name =
                    bundle.getString(REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME)
                val nam =
                    bundle.getString(REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAM)

                val ngayThang =
                    bundle.getString(REQUEST_KEY_SINGLE_CHOICE_SELECTED_NGAY_THANG)
                onResultSingleChoiceUsingTimeType(name, nam, ngayThang)
            } else {
                val name =
                    bundle.getString(REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME)
                val id =
                    bundle.getString(REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID)
                onResultSingleChoice(title, id, name)
            }
        }
    }

    open fun onResultSingleChoiceUsingTimeType(name: String?, nam: String?, ngayThang: String?) {
        // TODO: For override

    }

    fun setDataMultiChoiceCallBack(intent: Intent?) {
        intent?.getBundleExtra(KEY_BUNDLE_DATA)?.let { bundle ->
            val key =
                bundle.getInt(REQUEST_KEY_MULTI_CHOICE_KEY)
            Timber.d("ID Multichoice setDataMultiChoiceCallBack: ${key}")
            val data =
                bundle.getString(REQUEST_KEY_MULTI_CHOICE_SELECTED)
            val result = data?.toMutableList<SelectorItem>() ?: mutableListOf()
            Timber.d("onResulMultiChoice $bundle")
            onResultMultiChoice(key, result)
        }
    }


    open fun onResultSingleChoice(title: Int, id: String?, name: String?) {
        // TODO: For override
    }


    open fun onResultMultiChoice(title: Int, list: MutableList<SelectorItem>) {
        // TODO: For override
    }

}
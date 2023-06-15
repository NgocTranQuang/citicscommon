package citics.sharing.activity.base

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewbinding.ViewBinding
import citics.sharing.data.model.others.ChooserItem
import citics.sharing.data.model.others.SelectorItem
import citics.sharing.data.model.others.SingleChoiceData
import citics.sharing.extension.toMutableList
import citics.sharing.utils.*
import com.citics.valuation.ui.activity.choice.SingleChoiceActivity
import com.citics.valuation.ui.activity.choice.SingleChoiceAndAddingDataActivity
import com.sharing.R
import timber.log.Timber

abstract class BaseChooserFragment<V : ViewBinding, VM : BaseViewModel>(private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> V) :
    BaseFragment<V, VM>(bindingInflater) {

    val startSingleChoiceAndAddingForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val intent = it.data
            setDataSingleChoiceAndAddingCallBack(intent)
        }
    }

    val startSingleChoiceForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val intent = it.data
            setDataSingleChoiceCallBack(intent)
        }
    }

    val startMultiChoiceForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val intent = it.data
            setDataMultiChoiceCallBack(intent)
        }
    }

    fun goToSingleChoiceScreen(
        data: SingleChoiceData
    ) {
        startSingleChoiceForResult.launch(
            SingleChoiceActivity.newIntent(requireContext(), data)
        )
    }

    fun goToChooseScreen(
        toTypedArray: List<ChooserItem>, type: Int, chooserItem: ChooserItem
    ) {
        startSingleChoiceAndAddingForResult.launch(
            SingleChoiceAndAddingDataActivity.newIntent(
                requireContext(),
                SingleChoiceData(title = 0, lstData = toTypedArray, selected = null),
                chooserItem = chooserItem,
                type = type
            )
        )
    }

//    abstract fun onDataSingleChoiceAndAddingCallBack(intent: Intent?)
//    abstract fun onDataSingleChoiceCallBack(intent: Intent?)
//    abstract fun onDataMultiChoiceCallBack(intent: Intent?)

    open fun setDataSingleChoiceCallBack(intent: Intent?) {
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

    open fun setDataMultiChoiceCallBack(intent: Intent?) {
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


    open fun setDataSingleChoiceAndAddingCallBack(intent: Intent?) {
        // TODO: For override

    }

    open fun onResultSingleChoice(title: Int, id: String?, name: String?) {
        // TODO: For override
    }


    open fun onResultMultiChoice(title: Int, list: MutableList<SelectorItem>) {
        // TODO: For override
    }


}
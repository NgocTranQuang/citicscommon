package citics.sharing.activity.choice

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import citics.sharing.activity.base.BaseActivity
import citics.sharing.activity.base.BaseViewModel
import citics.sharing.adapter.choice.SingleChoiceAdapter
import citics.sharing.data.model.others.ChooserItem
import citics.sharing.data.model.others.SingleChoiceData
import citics.sharing.extension.getListParcelable
import citics.sharing.utils.*
import com.sharing.databinding.FragmentChoiceThoiHanSddBinding

class SingleChoiceTHSDDActivity :
    BaseActivity<FragmentChoiceThoiHanSddBinding, BaseViewModel>() {


    override val viewModel: BaseViewModel by viewModels()
    private var title: Int? = null
    private var selected: String? = null
    private var lstData: MutableList<ChooserItem>? = null
    private var adapter: SingleChoiceAdapter? = null

    companion object {
//        const val REQUEST_KEY_SINGLE_CHOICE = "REQUEST_KEY_SINGLE_CHOICE"
//        const val REQUEST_KEY_SINGLE_CHOICE_KEY = "REQUEST_KEY_SINGLE_CHOICE_KEY"
//        const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID = "REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID"
//        const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME =
//            "REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME"
//
//        const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_NGAY_THANG =
//            "REQUEST_KEY_SINGLE_CHOICE_SELECTED_NGAY_THANG"
//
//        const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAM =
//            "REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAM"

        fun newIntent(
            context: Context,
            data: SingleChoiceData
        ): Intent {
            val bundle = bundleOf(
                TITLE to data.title,
                LST_DATA to data.lstData?.toTypedArray(),
                SELECTED to data.selected,
                HAS_OTHER to data.hasOther,
                KEY_MESSAGE_NO_DATA to data.messageNodata
            )
            val intent = Intent(context, SingleChoiceTHSDDActivity::class.java)
            intent.putExtra(KEY_BUNDLE_DATA, bundle)
            return intent
        }
    }

    override fun onConfigUI() {
        super.onConfigUI()
        getDataIntent()
        handleUI()
    }

    private fun handleUI() {
        binding.headerLayout.setTitle(getString(title ?: 0))
        adapter = SingleChoiceAdapter(this, selected, lstData ?: mutableListOf()) {
            checkEnableButton()
        }
        binding.rvRadio.adapter = adapter
        checkEnableButton()
        binding.headerLayout.onBackClickListener = {
            finish()
        }
    }

    private fun getDataIntent() {
        val args = intent.getBundleExtra(KEY_BUNDLE_DATA)
        title = args?.getInt(TITLE)
        selected = args?.getString(SELECTED)
        lstData = args?.getListParcelable(LST_DATA)
    }

    override fun onObserverData() {
        super.onObserverData()
        binding.btnUpdate.onClickListener {
            adapter?.getSelected()?.let {
                val bundle = bundleOf(
                    REQUEST_KEY_SINGLE_CHOICE_KEY to title,
                    REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID to it.id,
                    REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME to it.name,
                    REQUEST_KEY_SINGLE_CHOICE_SELECTED_NGAY_THANG to binding.vNgayThangHetHan.getText()
                        .toString(),
                    REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAM to binding.vNamHetHan.getText()
                        .toString()
                )
                val intent = Intent()
                intent.putExtra(KEY_BUNDLE_DATA, bundle)
                setResult(RESULT_OK, intent);
                finish()
            }
        }
        binding.vNamHetHan.onChangeTextField = {
            checkEnableButton()
        }
    }


    private fun checkEnableButton() {
        if (adapter?.getSelected()?.name == UsingTimeType.LAU_DAI.type) {
            binding.btnUpdate.enableButton()
            binding.vTime.visibility = View.INVISIBLE
        } else if (adapter?.getSelected()?.name == UsingTimeType.CO_THOI_HAN.type) {
            binding.vTime.visibility = View.VISIBLE
            if (binding.vNamHetHan.getText().toString() == ""
            ) {
                binding.btnUpdate.disableButton()
            } else {
                binding.btnUpdate.enableButton()
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> FragmentChoiceThoiHanSddBinding
        get() = FragmentChoiceThoiHanSddBinding::inflate
}
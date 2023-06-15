package com.citics.valuation.ui.activity.choice

import android.content.Context
import android.content.Intent
import android.text.TextUtils
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
import com.sharing.R
import com.sharing.databinding.ActivityChoiceBinding
import timber.log.Timber

/**
 * Created by ChinhQT on 28/02/2023.
 */
class SingleChoiceActivity : BaseActivity<ActivityChoiceBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModels()
    private var title: Int? = null
    private var selected: String? = null
    private var messageNoData: String? = null
    private var lstData: MutableList<ChooserItem>? = null
    private var adapter: SingleChoiceAdapter? = null
    private var hasOther: Boolean = false

    override fun onConfigUI() {
        super.onConfigUI()
        val args = intent.getBundleExtra(KEY_BUNDLE_DATA)
        title = args?.getInt(TITLE)
        selected = args?.getString(SELECTED)
        lstData = args?.getListParcelable(LST_DATA)
        hasOther = args?.getBoolean(HAS_OTHER) ?: false
        messageNoData = args?.getString(KEY_MESSAGE_NO_DATA)

        binding.headerLayout.setTitle(getString(title ?: 0))
        if (hasOther) {
            // Có khác khác => Add item khác vào list
            lstData?.add(
                ChooserItem(
                    name = KEY_KHAC,
                    id = KEY_KHAC
                )
            )
            if (!TextUtils.isEmpty(selected)) {
                // Đã có selected
                val itemSelectedInList = lstData?.firstOrNull { it.name == selected }
                if (itemSelectedInList == null) {
                    // Thằng đã selected không có trong list truyền vào,
                    // Chứng tỏ nó là khác khác => Check vào khác và show edittext khác khác
                    binding.tfKhac.visibility = View.VISIBLE
                    binding.tfKhac.setText(selected)
                    selected = KEY_KHAC
                }
            }

            lstData?.sortBy { it.name == KEY_KHAC }
        }

        adapter = SingleChoiceAdapter(this, selected, lstData ?: mutableListOf()) {
            checkButtonDisable()
        }
        binding.rvRadio.adapter = adapter
        checkButtonDisable()
        binding.tfKhac.onChangeTextField = {
            if (TextUtils.isEmpty(it)) {
                binding.btnUpdate?.disableButton()
            } else {
                binding.btnUpdate?.enableButton()
            }
        }
        if ((adapter?.itemCount ?: 0) == 0) {
            if (TextUtils.isEmpty(messageNoData)) {
//                binding?.statefulLayout?.showContent()
            } else {
                binding.statefulLayout.showEmpty(messageNoData ?: "")
            }
        } else {
//            binding?.statefulLayout?.showContent()
        }
        val hint = getString(R.string.nhap_ten) + " " + getString(
            title ?: 0
        ).lowercase() + " " + getString(R.string.khac).lowercase()
        binding.tfKhac.bindData(
            hint,
            false,
            null,
            getString(R.string.vui_long_nhap),
            null,
            null,
            ""
        )
        binding.headerLayout.onBackClickListener = {
            finish()
        }
    }


    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }


    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }

    private fun checkButtonDisable() {
        val itemChecked = adapter?.getSelected()
        if (itemChecked != null) {
            if (itemChecked.name == KEY_KHAC) {
                binding.tfKhac.visibility = View.VISIBLE
                binding.sroll.post {
                    binding.sroll.fullScroll(View.FOCUS_DOWN)
                }

                if (TextUtils.isEmpty(binding.tfKhac.getText().toString())) {
                    binding.btnUpdate.disableButton()
                } else {
                    binding.btnUpdate.enableButton()
                }
            } else {
                binding.tfKhac.visibility = View.GONE
                binding.btnUpdate.enableButton()
            }
        } else {
            binding.btnUpdate.disableButton()
        }
    }

    override fun onObserverData() {
        super.onObserverData()
        binding.btnUpdate.onClickListener {
            adapter?.getSelected()?.let {
                var name = it.name
                if (name == KEY_KHAC) {
                    name = binding.tfKhac.getText()?.toString() ?: ""
                }
                val bundle = bundleOf(
                    REQUEST_KEY_SINGLE_CHOICE_KEY to title,
                    REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID to it.id,
                    REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME to name
                )
                val intent = Intent()
                intent.putExtra(KEY_BUNDLE_DATA, bundle)
                setResult(RESULT_OK, intent);
                finish();
                Timber.d("setFragmentResult $bundle")
//                setFragmentResult(SingleChoiceFragment.REQUEST_KEY_SINGLE_CHOICE, bundle)
//                setBackStackResult(SingleChoiceFragment.REQUEST_KEY_SINGLE_CHOICE, bundle)
            }
            finish();
        }
    }

    companion object {
//        const val REQUEST_KEY_SINGLE_CHOICE = "REQUEST_KEY_SINGLE_CHOICE"
//        const val REQUEST_KEY_SINGLE_CHOICE_KEY = "REQUEST_KEY_SINGLE_CHOICE_KEY"
//        const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID = "REQUEST_KEY_SINGLE_CHOICE_SELECTED_ID"
//        const val REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME =
//            "REQUEST_KEY_SINGLE_CHOICE_SELECTED_NAME"
//
//        const val TITLE = "title"
//        const val LST_DATA = "lstData"
//        const val SELECTED = "selected"
//        const val HAS_OTHER = "HAS_OTHER"
//        const val KEY_MESSAGE_NO_DATA = "KEY_MESSAGE_NO_DATA"
//
//        const val KEY_BUNDLE_DATA = "KEY_BUNDLE_DATA"


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
            val intent = Intent(context, SingleChoiceActivity::class.java)
            intent.putExtra(KEY_BUNDLE_DATA, bundle)
            return intent
        }
    }

    override val bindingInflater: (LayoutInflater) -> ActivityChoiceBinding
        get() = ActivityChoiceBinding::inflate
}
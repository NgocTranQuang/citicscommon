package citics.sharing.activity.choice

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import citics.sharing.activity.base.BaseActivity
import citics.sharing.activity.base.BaseViewModel
import citics.sharing.adapter.choice.MultiChoiceNewAdapter
import citics.sharing.data.model.others.ChooserItem
import citics.sharing.data.model.others.MultiChoiceData
import citics.sharing.extension.getListParcelable
import citics.sharing.extension.getListString
import citics.sharing.utils.*
import com.google.gson.Gson
import com.sharing.R
import com.sharing.databinding.ActivityChoiceBinding
import timber.log.Timber

/**
 * Created by ChinhQT on 28/02/2023.
 */
class MultiChoiceActivity : BaseActivity<ActivityChoiceBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel = BaseViewModel()


    private var adapter: MultiChoiceNewAdapter? = null

    private var title: Int? = null
    private var hasImageTitle: Boolean? = null
    private var hasOther: Boolean = false
    private var lstData: MutableList<ChooserItem>? = null
    private var lstSelected: MutableList<String>? = null

    override fun onConfigUI() {
        super.onConfigUI()
        handleDataIntent()
        handleUI()
        onClick()
    }

    private fun onClick() {
        binding.headerLayout.onBackClickListener = {
            finish()
        }
        binding.btnUpdate.onClickListener {
            adapter?.getListSelected()?.let { list ->
                val itemKhac = list.firstOrNull { item -> item.name == KEY_KHAC }
                if (itemKhac != null && hasOther) {
                    itemKhac.name = binding?.tfKhac?.getText()?.toString() ?: ""
                    itemKhac.isCustomData = true
                }
                val data = Gson().toJson(list)
                val bundle = bundleOf(
                    REQUEST_KEY_MULTI_CHOICE_KEY to title,
                    REQUEST_KEY_MULTI_CHOICE_SELECTED to data
                )
                val intent = Intent()
                intent.putExtra(KEY_BUNDLE_DATA, bundle)
                Timber.d("ID Multichoice btnUpdate: ${title}")
                setResult(RESULT_OK, intent);
            }
            finish()
        }
    }

    private fun handleUI() {
        val itemKhac = lstSelected?.firstOrNull { itemInListSelected ->
            lstData?.map { it.name }?.contains(itemInListSelected) != true
        }
        if (hasOther) {
            // Có khác khác
            lstData?.add(
                ChooserItem(
                    name = KEY_KHAC,
                    id = itemKhac ?: ""
                ).apply {
                    isSelected = !TextUtils.isEmpty(itemKhac)
                }
            )
        }
        lstData?.filter { lstSelected?.contains(it.name) == true }?.forEach { it.isSelected = true }
        lstData?.sortBy { it.name == KEY_KHAC }
        adapter = MultiChoiceNewAdapter(
            this,
            hasImageTitle ?: false,
            lstData ?: mutableListOf()
        ) {
            checkEnableButton(it)
        }
        binding.rvRadio.adapter = adapter
        binding.headerLayout.setTitle(getString(title ?: 0))
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
        binding.tfKhac.setText(itemKhac) {
            enableButton()
        }
        checkEnableButton("")
    }


    private fun handleDataIntent() {
        val args = intent.getBundleExtra(KEY_BUNDLE_DATA)
        title = args?.getInt(TITLE)
        Timber.d("ID Multichoice handleDataIntent: ${title}")
        hasOther = args?.getBoolean(HAS_OTHER) ?: false
        if (title == R.string.muc_dich_su_dung_dat) {
            hasImageTitle = true
        }
        lstData = args?.getListParcelable(LST_DATA)
        lstSelected = args?.getListString(LST_SELECTED)
    }

    private fun checkEnableButton(justCheckTitile: String) {
        val listSelected = lstData?.filter { it.isSelected } ?: listOf()
        if (listSelected.isNotEmpty()) {
            if (listSelected.firstOrNull { it.name == KEY_KHAC } != null && hasOther) {
                binding.tfKhac.visibility = View.VISIBLE
                if (justCheckTitile == KEY_KHAC) {
                    binding.sroll.post {
                        binding.sroll.fullScroll(View.FOCUS_DOWN)
                    }
                }
                if (TextUtils.isEmpty(binding.tfKhac.getText().toString())) {
                    disableButton()
                } else {
                    enableButton()
                }
            } else {
                binding.tfKhac.visibility = View.GONE
                enableButton()
            }

        } else {
            binding.tfKhac.visibility = View.GONE
            disableButton()
        }
    }


    private fun disableButton() {
        // binding?.btnUpdate?.disableButton()
        // Luôn luôn enable
        binding.btnUpdate.enableButton()

    }

    private fun enableButton() {
        binding.btnUpdate.enableButton()
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }


    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }


    companion object {

//        const val REQUEST_KEY_MULTI_CHOICE = "REQUEST_KEY_MULTI_CHOICE"
//        const val REQUEST_KEY_MULTI_CHOICE_KEY = "REQUEST_KEY_MULTI_CHOICE_KEY"
//        const val REQUEST_KEY_MULTI_CHOICE_SELECTED = "REQUEST_KEY_MULTI_CHOICE_SELECTED"
//
//        // KEY_ARGUMENT
//        const val TITLE = "title"
//        const val LST_DATA = "lstData"
//        const val LST_SELECTED = "lstSelected"
//        const val HAS_OTHER = "HAS_OTHER"

        fun newIntent(
            context: Context,
            data: MultiChoiceData
        ): Intent {
            Timber.d("ID Multichoice newIntent: ${data.title}")
            val bundle = bundleOf(
                TITLE to data.title,
                LST_DATA to data.lstData?.toTypedArray(),
                LST_SELECTED to data.selected,
                HAS_OTHER to data.hasOther
            )
            val intent = Intent(context, MultiChoiceActivity::class.java)
            intent.putExtra(KEY_BUNDLE_DATA, bundle)
            return intent
        }
    }

    override val bindingInflater: (LayoutInflater) -> ActivityChoiceBinding
        get() = ActivityChoiceBinding::inflate
}
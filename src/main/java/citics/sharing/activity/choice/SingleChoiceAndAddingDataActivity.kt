package com.citics.valuation.ui.activity.choice

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import citics.sharing.activity.base.BaseActivity
import citics.sharing.activity.base.BaseViewModel
import citics.sharing.adapter.choice.ChooserAdapter
import citics.sharing.data.model.others.ChooserItem
import citics.sharing.data.model.others.SingleChoiceData
import citics.sharing.extension.getData
import citics.sharing.extension.getListParcelable
import citics.sharing.utils.KEY_BUNDLE_DATA
import com.sharing.R
import com.sharing.databinding.FragmentDataChooserBinding

/**
 * Created by ChinhQT on 28/02/2023.
 */
class SingleChoiceAndAddingDataActivity :
    BaseActivity<FragmentDataChooserBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModels()
    private var typeChooser: Int = 0
    private var chooserItem: ChooserItem? = null
    private var chooserItemFull: MutableList<ChooserItem>? = null

    private var chooserAdapter: ChooserAdapter? = null

    override fun onConfigUI() {
        super.onConfigUI()
        getDataIntent()
        binding.headerLayout.onBackClickListener = {
            finish()
        }

        binding.searchLayout.visibility = View.VISIBLE
        binding.searchLayout.getTextInput()?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    binding?.addBtn?.text = getString(
                        R.string.tdg_add,
                        binding?.headerLayout?.getTitle()?.lowercase() + " " + s.toString()
                    )
                    binding?.addBtn?.visibility = View.VISIBLE
                    val newList = chooserItemFull?.filter {
                        it.name.lowercase().contains(s.toString().lowercase())
                    }
                    chooserAdapter?.submitList(newList?.toMutableList())
                } else {
                    binding?.addBtn?.visibility = View.GONE
                    chooserAdapter?.submitList(chooserItemFull?.toMutableList())

                }
            }

        })

        when (typeChooser) {
            LCH_TYPE -> {
                setTitle(getString(R.string.loai_can_ho))
            }
            TANG_TYPE -> {
                setTitle(getString(R.string.tang))
            }
            HUONG_TYPE -> {
                setTitle(getString(R.string.huong_nha))
            }
            THAP_TYPE -> {
                setTitle(getString(R.string.thap))
            }
            MCH_TYPE -> {
                setTitle(getString(R.string.ma_can_ho))
            }
        }

        chooserAdapter = ChooserAdapter(chooserItem) {
            chooserItem = it

            val bundle = bundleOf(
                LCH_DATA_KEY to chooserItem,
                LCH_TYPE_KEY to typeChooser
            )

            val intent = Intent()
            intent.putExtra(KEY_BUNDLE_DATA, bundle)
            setResult(RESULT_OK, intent);
            finish();
        }
        setupRecyclerView()
        binding.dataList.adapter = chooserAdapter
        chooserAdapter?.submitList(chooserItemFull)

        binding.addBtn.setOnClickListener {
            val item = binding.searchLayout.getTextInput().text.toString()
            chooserItem = ChooserItem(item.hashCode().toString(), item, isCustomData = true)

            val bundle = bundleOf(
                LCH_DATA_KEY to chooserItem,
                LCH_TYPE_KEY to typeChooser
            )
            val intent = Intent()
            intent.putExtra(KEY_BUNDLE_DATA, bundle)
            setResult(RESULT_OK, intent)
            finish()
        }
    }


    private fun getDataIntent() {
        val bundle = intent.getBundleExtra(KEY_BUNDLE_DATA)
        typeChooser = bundle?.getInt(TYPE_ITEM) ?: THAP_TYPE
        chooserItem = bundle?.getData(CHOOSER_ITEM)
        chooserItemFull = bundle?.getListParcelable(DATA)

    }

    fun setTitle(title: String) {
        binding?.headerLayout?.setTitle(title)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.dataList?.layoutManager = layoutManager
    }


    companion object {

        const val DATA = "data"
        const val CHOOSER_ITEM = "choserItem"
        const val TYPE_ITEM = "typeItem"

        const val REQUEST_KEY = "LOAI_CAN_HO_KEY"
        const val LCH_DATA_KEY = "LCH_DATA_KEY"
        const val LCH_TYPE_KEY = "LCH_TYPE_KEY"

        const val LCH_TYPE = 1
        const val TANG_TYPE = 2
        const val HUONG_TYPE = 3
        const val THAP_TYPE = 4
        const val MCH_TYPE = 5


        fun newIntent(
            context: Context,
            data: SingleChoiceData,
            chooserItem: ChooserItem?,
            type: Int
        ): Intent {
            val bundle = bundleOf(
                DATA to data.lstData?.toTypedArray(),
                CHOOSER_ITEM to chooserItem,
                TYPE_ITEM to type
            )
            val intent = Intent(context, SingleChoiceAndAddingDataActivity::class.java)
            intent.putExtra(KEY_BUNDLE_DATA, bundle)
            return intent
        }
    }

    override val bindingInflater: (LayoutInflater) -> FragmentDataChooserBinding
        get() = FragmentDataChooserBinding::inflate

}
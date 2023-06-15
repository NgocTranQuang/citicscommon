package citics.sharing.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import citics.sharing.adapter.base.BaseAdapter
import citics.sharing.data.model.response.LandDTO
import com.citics.valuation.data.model.response.RequiredLand
import citics.sharing.extension.getDrawableFromName
import citics.sharing.extension.toShow
import com.sharing.R
import com.sharing.databinding.LayoutRowLandBinding
import timber.log.Timber

class LandAdapter(val context: Context, val listData: MutableList<LandDTO>) :
    BaseAdapter<LayoutRowLandBinding, LandDTO>(context, listData) {
    var listValiDation: List<RequiredLand>? = null
    var onChange: (() -> Unit)? = null
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> LayoutRowLandBinding
        get() = LayoutRowLandBinding::inflate

    @SuppressLint("SetTextI18n")
    override fun LandDTO.handleItem(binding: LayoutRowLandBinding?, position: Int) {
        Timber.d("$position : ${this}")
        fun tinhTong() {
            dien_tich_dat = (dien_tich_dat_vpqh ?: 0.0f) + (dien_tich_dat_phqh ?: 0.0f)
            binding?.tvDienTichDat?.text =
                "${dien_tich_dat.toShow()} ${context.getString(R.string.m2)}"
            onChange?.invoke()
        }
        binding?.tvTitle?.text = name
        binding?.tvTitle?.setDrawableStartTextview(binding.root.context?.getDrawableFromName("ic_mdsdd_" + kind?.lowercase()))
        binding?.tfVPQH?.setText(dien_tich_dat_vpqh) {
            Timber.d("${position} - ${this.name} - set diện tích đất phù hợp quy hoạch : ${it}")
            dien_tich_dat_vpqh = it
            tinhTong()
        }
        binding?.tfPHQH?.setText(dien_tich_dat_phqh) {
            Timber.d("${position} - ${this.name} - set diện tích đất vi phạm quy hoạch : ${it}")
            dien_tich_dat_phqh = it
            tinhTong()
        }
        binding?.tvDienTichDat?.text =
            "${(dien_tich_dat ?: 0f).toShow()} ${context.getString(R.string.m2)}"
        if (position == (mainList?.size ?: 0) - 1) {
            // the end
            binding?.root?.hideBottomLine()
        } else {
            binding?.root?.showBottomLine()
        }

        val item = listValiDation?.firstOrNull { it.name == name }
        val isError = item?.fields?.firstOrNull { it == "dien_tich_dat" } != null

        if (isError) {
            binding?.tfPHQH?.setState(true)
            binding?.tfVPQH?.setState(true)
        } else {
            binding?.tfPHQH?.setState(false)
            binding?.tfVPQH?.setState(false)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun checkValidation(it: List<RequiredLand>?) {
        this.listValiDation = it
        notifyDataSetChanged()
    }
}
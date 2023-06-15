package citics.sharing.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import citics.sharing.adapter.base.BaseAdapter
import citics.sharing.data.model.response.DetailUsingPurpose
import com.citics.valuation.data.model.response.RequiredLand
import citics.sharing.utils.UsingTimeType
import com.sharing.R
import com.sharing.databinding.RowMucDichSuDungDatThamDinhBinding

class MucDichSuDungDatAdapter(
    context: Context,
    lst: MutableList<DetailUsingPurpose>,
    val showValidataion: Boolean
) :BaseAdapter<RowMucDichSuDungDatThamDinhBinding, DetailUsingPurpose>(context, lst) {
    var onClickChonThoiHan: ((DetailUsingPurpose) -> Unit)? = null
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> RowMucDichSuDungDatThamDinhBinding
        get() = RowMucDichSuDungDatThamDinhBinding::inflate
    var listValiDation: List<RequiredLand>? = null

    @SuppressLint("SetTextI18n")
    override fun DetailUsingPurpose.handleItem(
        binding: RowMucDichSuDungDatThamDinhBinding?,
        position: Int
    ) {
        binding?.chip?.text = title
        if (using_time_type == null) {
            binding?.chip2?.text = mContext.getString(R.string.chon_thoi_han)
        } else {
            if (using_time_type == UsingTimeType.LAU_DAI.type) {
                binding?.chip2?.text = using_time_type
            } else {
                val day_month = if (TextUtils.isEmpty(day_month)) "" else "${day_month}/"
                binding?.chip2?.text = "${day_month}${year}"
            }
        }
        binding?.chip2?.setOnClickListener {
            onClickChonThoiHan?.invoke(this)
        }
        if (showValidataion) {
            val item = listValiDation?.firstOrNull { it.name == name }
            val isError = item?.fields?.firstOrNull { it == "using_purpose" } != null
            if (isError) {
                // Vi phạm => bôi viền vàng , set chữ vàng
                binding?.chip2?.setChipStrokeColorResource(es.dmoral.toasty.R.color.warningColor)
                binding?.chip2?.setChipStrokeWidthResource(R.dimen.dimen_1)
                binding?.chip2?.setTextColor(binding.root.context.getColor(es.dmoral.toasty.R.color.warningColor))

            } else {
                binding?.chip2?.setChipStrokeColorResource(R.color.color_btn_background)
                binding?.chip2?.setChipStrokeWidthResource(R.dimen.dimen_1)
                binding?.chip2?.setTextColor(binding.root.context.getColor(R.color.color_icon_tint))
            }
        } else {
            binding?.chip2?.setChipStrokeColorResource(R.color.color_btn_background)
            binding?.chip2?.setChipStrokeWidthResource(R.dimen.dimen_1)
            binding?.chip2?.setTextColor(binding.root.context.getColor(R.color.text_main))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun checkValidation(it: List<RequiredLand>?) {
        this.listValiDation = it
        notifyDataSetChanged()
    }
}
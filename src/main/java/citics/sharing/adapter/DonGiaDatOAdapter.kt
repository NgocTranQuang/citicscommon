package citics.sharing.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import citics.sharing.adapter.base.BaseAdapter
import citics.sharing.data.model.response.LandDTO
import com.sharing.databinding.ItemRowDongiaBinding


open class DonGiaDatOAdapter(
    context: Context,
    lst: MutableList<LandDTO>
) :
    BaseAdapter<ItemRowDongiaBinding, LandDTO>(context, lst) {
    var onClick: (LandDTO) -> Unit = {}
    var onDelete: (LandDTO) -> Unit = {}
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemRowDongiaBinding
        get() = ItemRowDongiaBinding::inflate

    override fun LandDTO.handleItem(binding: ItemRowDongiaBinding?, position: Int) {
        binding?.dongiadato?.setTitle("Đơn giá ${name?.lowercase()}")
        binding?.dongiadato?.setHint("Nhập đơn giá ${name?.lowercase()}")
        binding?.dongiadato?.setText(don_gia_ubnd) {
            don_gia_ubnd = it
        }
    }
}
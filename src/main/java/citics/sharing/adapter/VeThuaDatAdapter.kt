package citics.sharing.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import citics.sharing.adapter.base.BaseAdapter
import citics.sharing.customview.TongGiaTriLayout
import citics.sharing.data.model.response.LandDTO
import citics.sharing.extension.toShow
import citics.sharing.utils.Utils
import com.sharing.R
import com.sharing.databinding.RowVeThuaDatBinding

class VeThuaDatAdapter(
    context: Context,
    list: MutableList<LandDTO>,
    var type: TongGiaTriLayout.TongGiaTriType
) : BaseAdapter<RowVeThuaDatBinding, LandDTO>(context, list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> RowVeThuaDatBinding
        get() = RowVeThuaDatBinding::inflate

    @SuppressLint("SetTextI18n")
    override fun LandDTO.handleItem(
        binding: RowVeThuaDatBinding?,
        position: Int
    ) {
        var phqh: String? = null
        var vpqh: String? = null
        var suffix: String? = null
        when (type) {
            TongGiaTriLayout.TongGiaTriType.GIA_TRI -> {
                phqh = gia_tri_dat_phqh.toShow()
                vpqh = gia_tri_dat_vpqh.toShow()
                suffix = mContext.getString(R.string.d)
            }
            TongGiaTriLayout.TongGiaTriType.DON_GIA -> {
                phqh = don_gia_dat_phqh.toShow()
                vpqh = don_gia_dat_vpqh.toShow()
                suffix = mContext.getString(R.string.dm2)
            }
            TongGiaTriLayout.TongGiaTriType.DIEN_TICH -> {
                phqh = dien_tich_dat_phqh.toShow()
                vpqh = dien_tich_dat_vpqh.toShow()
                suffix = mContext.getString(R.string.m2)
            }
            TongGiaTriLayout.TongGiaTriType.ALL -> {
                binding?.lnExpandVPQH?.visibility = View.VISIBLE
                binding?.lnExpandPHQH?.visibility = View.VISIBLE
                binding?.vDienTich?.setValue(dien_tich_dat_phqh.toShow())
                binding?.vDonGia?.setValue(don_gia_dat_phqh.toShow())

                binding?.vDienTichVPQH?.setValue(dien_tich_dat_vpqh.toShow())
                binding?.vDonGiaVPQH?.setValue(don_gia_dat_vpqh.toShow())

                handleExpanedCollapse(binding?.imgExpand, binding?.lnDetailPHQH)
                handleExpanedCollapse(binding?.imgExpandVPQH, binding?.lnDetailVPQH)
            }
        }
        //We dont need the label 1.x, UI at https://app.zeplin.io/project/633bcdf13520692ec3b5a65a/screen/638086acb28d2a727a4f5531
//        binding?.tvSTT?.text = "1.${position + 1}"
        binding?.vDatO?.setUnit(suffix)
        binding?.vDatO?.setTitle(name ?: "")
        binding?.vDatO?.setDrawable(Utils.getDrawableMDSDDByName(mContext, kind))
//        binding?.vDatO?.setValue(dien_tich_dat.toShow())
        binding?.vPhuHopQuyHoach?.setUnit(suffix)
        binding?.vPhuHopQuyHoach?.setValue(phqh.toString())
        binding?.vViPhamQuyHoach?.setUnit(suffix)
        binding?.vViPhamQuyHoach?.setValue(vpqh.toString())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTypeData(field: TongGiaTriLayout.TongGiaTriType) {
        type = field
        notifyDataSetChanged()
    }
}
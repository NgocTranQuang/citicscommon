package citics.sharing.customview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import citics.sharing.data.model.response.CongTrinh
import com.citics.cbank.R
import com.citics.cbank.databinding.RowDienTichCongTrinhBinding
import com.citics.valuation.adapter.base.BaseAdapter
import citics.sharing.extension.toShow

class DienTichCongTrinhAdapter(
    context: Context,
    list: MutableList<CongTrinh>,
    var type: TongGiaTriLayout.TongGiaTriType
) :
    BaseAdapter<RowDienTichCongTrinhBinding, CongTrinh>(context, list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> RowDienTichCongTrinhBinding
        get() = RowDienTichCongTrinhBinding::inflate

    @SuppressLint("SetTextI18n")
    override fun CongTrinh.handleItem(
        binding: RowDienTichCongTrinhBinding?,
        position: Int
    ) {
        var dcn: String? = null
        var cpchc: String? = null
        var kcp: String? = null
        var suffix: String? = null
        when (type) {
            TongGiaTriLayout.TongGiaTriType.GIA_TRI -> {
                dcn = thanh_tien_gia_tri_cong_trinh_phqh.toString()
                cpchc = thanh_tien_gia_tri_cong_trinh_incomplete.toString()
                kcp = thanh_tien_gia_tri_cong_trinh_vpqh.toString()
                suffix = mContext.getString(R.string.d)
            }
            TongGiaTriLayout.TongGiaTriType.DON_GIA -> {
                dcn = don_gia_cong_trinh.toString()
                cpchc = don_gia_cong_trinh.toString()
                kcp = don_gia_cong_trinh.toString()
                suffix = mContext.getString(R.string.dm2)

            }
            TongGiaTriLayout.TongGiaTriType.DIEN_TICH -> {
                dcn = ((dien_tich_san_xay_dung_cac_tang_tren_phqh
                    ?: 0.0) + (dien_tich_san_xay_dung_tang_ham_phqh ?: 0.0)).toString()
                cpchc = ((dien_tich_san_xay_dung_cac_tang_tren_incomplete
                    ?: 0.0) + (dien_tich_san_xay_dung_tang_ham_incomplete ?: 0.0)).toString()
                kcp = ((dien_tich_san_xay_dung_cac_tang_tren_vpqh
                    ?: 0.0) + (dien_tich_san_xay_dung_tang_ham_vpqh ?: 0.0)).toString()
                suffix = mContext.getString(R.string.m2)
            }
            else -> {

            }
        }
        binding?.vChatLuongConLai?.setValue(ty_le_clcl_cong_trinh.toString())
        binding?.tvSTT?.text = "2.${position + 1}"
        binding?.vBonTangNoi?.setValue(gia_tri_cong_trinh.toString())
        binding?.vBonTangNoi?.setTitleAndImage(
            getHouseName(
                mContext,
                loaiCongTrinh ?: "",
                tangTren ?: 0,
                tangHam ?: 0
            ), 0
        )
        binding?.vDaCongNhan?.setValue(dcn)
        binding?.vDaCongNhan?.setUnit(suffix)
        binding?.vPhepChuaHoanCong?.setValue(cpchc)
        binding?.vPhepChuaHoanCong?.setUnit(suffix)
        binding?.vKhongCoPhep?.setValue(kcp)
        binding?.vKhongCoPhep?.setUnit(suffix)

        //Đã công nhận
        val dcnm = ((dien_tich_san_xay_dung_cac_tang_tren_phqh
            ?: 0.0) + (dien_tich_san_xay_dung_tang_ham_phqh ?: 0.0)).toShow()
        binding?.vDienTich?.setValue(dcnm)
        binding?.vDonGia?.setValue(don_gia_cong_trinh.toShow())

        // Có phép chưa hoàn công
        val cpchcm = ((dien_tich_san_xay_dung_cac_tang_tren_incomplete
            ?: 0.0) + (dien_tich_san_xay_dung_tang_ham_incomplete ?: 0.0)).toString()

        binding?.vDienTichCPCHC?.setValue(cpchcm)
        binding?.vDonGiaCPCHC?.setValue(don_gia_cong_trinh.toShow())

        // Không có phép
        val kcpm = ((dien_tich_san_xay_dung_cac_tang_tren_vpqh
            ?: 0.0) + (dien_tich_san_xay_dung_tang_ham_vpqh ?: 0.0)).toString()
        binding?.vDienTichKCP?.setValue(cpchcm)
        binding?.vDonGiaKCP?.setValue(don_gia_cong_trinh.toShow())
        handleExpanedCollapse(binding?.imgExpand, binding?.lnDetailDaCongNhan)
        handleExpanedCollapse(binding?.imgExpandCPCHC, binding?.lnDetailCPCHC)
        handleExpanedCollapse(binding?.imgExpandKCP, binding?.lnDetailKCP)
    }

    private fun getHouseName(
        context: Context,
        houseKind: String,
        tangNoi: Int,
        tangHam: Int
    ): String {
        val tangNoiStr = if (tangNoi > 0) {
            "$tangNoi ${context.getString(R.string.text_floor)}"
        } else {
            ""
        }
        val tangHamStr = if (tangHam > 0) {
            "$tangHam ${context.getString(R.string.text_basement)}"
        } else {
            ""
        }

        return "$houseKind $tangNoiStr $tangHamStr".trim()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTypeData(field: TongGiaTriLayout.TongGiaTriType) {
        type = field
        notifyDataSetChanged()
    }
}
//package com.citics.cagent.views.dfAssetData.nhadat.view
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.LinearLayout
//import com.citics.cagent.R
//import citics.sharing.data.model.response.CongTrinh
//import com.citics.cagent.databinding.LayoutDfChitietcongtrinhxaydungBinding
//import com.citics.cagent.utilities.LevelType
//import com.citics.cagent.utilities.extensions.setListChip
//import com.citics.cagent.utilities.extensions.toShow
//
//class LayoutDFNDViewChiTietCongTrinh @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : LinearLayout(context, attrs, defStyle, defStyleRes) {
//    private var binding: LayoutDfChitietcongtrinhxaydungBinding
//
//    init {
//        binding = LayoutDfChitietcongtrinhxaydungBinding.inflate(LayoutInflater.from(context), this)
//
//        attrs?.let {
//            onClick()
//        }
//    }
//
//    private fun onClick() {
//
//    }
//
//    fun setData(
//        congTrinhDTO: CongTrinh?,
//        assetLevel: String?
//    ) {
//        binding.run {
//            vLoaiCongTrinh.setValue(congTrinhDTO?.loaiCongTrinh)
//            vTongDienTich.setValue(
//                ((congTrinhDTO?.dien_tich_san_xay_dung_cac_tang_tren
//                    ?: 0.0) + (congTrinhDTO?.dien_tich_san_xay_dung_tang_ham ?: 0.0)).toShow()
//            )
//            if ((congTrinhDTO?.tangTren ?: 0) > 0) {
//                lnTangNoi.visibility = View.VISIBLE
//                vTangNoi.setValue(congTrinhDTO?.dien_tich_san_xay_dung_cac_tang_tren?.toShow())
//                vSoTang.setValue(congTrinhDTO?.tangTren?.toString())
//                vDaCongNhan.setValue(congTrinhDTO?.dien_tich_san_xay_dung_cac_tang_tren_phqh?.toShow())
//                vPhepChuaHoanCong.setValue(congTrinhDTO?.dien_tich_san_xay_dung_cac_tang_tren_incomplete?.toShow())
//                vKhongCoPhep.setValue(congTrinhDTO?.dien_tich_san_xay_dung_cac_tang_tren_vpqh?.toShow())
//            }
//
//            if ((congTrinhDTO?.tangHam ?: 0) > 0) {
//                lnTangHam.visibility = View.VISIBLE
//                vTangHam.setValue(congTrinhDTO?.dien_tich_san_xay_dung_tang_ham?.toShow())
//                vSoTangHam.setValue(congTrinhDTO?.tangHam?.toString())
//                vDaCongNhanHam.setValue(congTrinhDTO?.dien_tich_san_xay_dung_tang_ham_phqh?.toShow())
//                vPhepChuaHoanCongHam.setValue(congTrinhDTO?.dien_tich_san_xay_dung_tang_ham_incomplete?.toShow())
//                vKhongCoPhepHam.setValue(congTrinhDTO?.dien_tich_san_xay_dung_tang_ham_vpqh?.toShow())
//            }
//            vNamSuaChua.setValue(congTrinhDTO?.nam_sua_chua?.toString())
//            vNamXayDung.setValue(congTrinhDTO?.nam_xay_dung?.toString())
//            vPhongNgu.setValue(congTrinhDTO?.so_phong_ngu?.toString())
//            vPhongVeSinh.setValue(congTrinhDTO?.so_wc?.toString())
//            vPhongKhach.setValue(congTrinhDTO?.so_phong_khach?.toString())
//            vPhongBep.setValue(congTrinhDTO?.so_phong_bep?.toString())
//            vCapNha.setValue(congTrinhDTO?.cap_nha)
//            vKetCau.setValue(congTrinhDTO?.ket_cau_nha)
//            vCoSoHaTangKyThuatChip.setListChip(congTrinhDTO?.co_so_ha_tang_ky_thuat?.toMutableList())
//            vSoSanhVoiThucTeChip.setListChip(
//                mutableListOf(
//                    congTrinhDTO?.so_sanh_thuc_te_co_so_ha_tang_ky_thuat ?: ""
//                )
//            )
//            tvMoTaCongTrinh.text = congTrinhDTO?.mo_ta_cong_trinh
//            tvGhiChu.text = congTrinhDTO?.so_sanh_thuc_te_co_so_ha_tang_ky_thuat_khac
//            vDonGiaXayDung.setValue(congTrinhDTO?.don_gia_cong_trinh?.toShow())
//            vChatLuongConLai.setValue(congTrinhDTO?.ty_le_clcl_cong_trinh?.toShow())
//        }
//        handleUiByLevel(assetLevel)
//    }
//
//    private fun handleUiByLevel(assetLevel: String?) {
//        binding.run {
//            when (assetLevel) {
//                LevelType.TRA_CUU.type -> {
//                    lnInfoTangNoi.visibility = View.GONE
//                    lnTangHam.visibility = GONE
//                    vCapNha.visibility = GONE
//                    vKetCau.visibility = GONE
//                    tvMoTaCongTrinh.visibility = GONE
//                    lnCauTrucPhong.visibility = GONE
//                    vCoSoHaTangKyThuat.visibility = GONE
//                    vSoSanhVoiThucTeChip.visibility = GONE
//                    lnGhiChu.visibility = GONE
//                    vDonGiaXayDung.visibility = GONE
//                    vChatLuongConLai.visibility = GONE
//                    vTangNoi.setValue("")
//                }
//                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
//                    lnInfoTangNoi.visibility = View.GONE
//                    lnTangHam.visibility = GONE
//                    vCapNha.visibility = GONE
//                    vKetCau.visibility = GONE
//                    tvMoTaCongTrinh.visibility = GONE
//                    lnCauTrucPhong.visibility = GONE
//                    vCoSoHaTangKyThuat.visibility = GONE
//                    vSoSanhVoiThucTeChip.visibility = GONE
//                    lnGhiChu.visibility = GONE
//                    vDonGiaXayDung.visibility = GONE
//                    vChatLuongConLai.visibility = GONE
//                    vTangNoi.setValue("")
//
//                }
//                LevelType.MUA_BAN.type -> {
//                    lnInfoTangNoi.visibility = GONE
//                    lnInfoTangHam.visibility = GONE
//                    vCapNha.visibility = GONE
//                    tvMoTaCongTrinh.visibility = GONE
//                    vCoSoHaTangKyThuat.visibility = GONE
//                    vSoSanhVoiThucTeChip.visibility = GONE
//                    lnGhiChu.visibility = GONE
//                    vDonGiaXayDung.visibility = GONE
//                    vChatLuongConLai.visibility = GONE
//                }
//                LevelType.THAM_DINH.type -> {
//                    vDonGiaXayDung.visibility = GONE
//                    vChatLuongConLai.visibility = GONE
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//                    vCapNha.visibility= GONE
//                    tvMoTaCongTrinh.visibility= GONE
//                    lnCauTrucPhong.visibility= GONE
//                    vCoSoHaTangKyThuat.visibility = GONE
//                    vSoSanhVoiThucTeChip.visibility = GONE
//                    lnGhiChu.visibility = GONE
//                }
//            }
//        }
//    }
//}
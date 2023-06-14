//package com.citics.cagent.views.dfAssetData.nhadat.view
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.widget.LinearLayout
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.findNavController
//import com.citics.cagent.R
//import citics.sharing.data.model.response.DetailUsingPurpose
//import citics.sharing.data.model.response.LandDTO
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfNdHientrangthuadatBinding
//import com.citics.cagent.databinding.LayoutDfNdViewChitietthuadatBinding
//import com.citics.cagent.databinding.LayoutDfNdViewHientrangthuadatBinding
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.DonGiaDatOAdapter
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.DonGiaDatOViewAdapter
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.MucDichSuDungDatAdapter
//import com.citics.cagent.ui.fragments.thamdinh.chitiet.ChiTietDienTichThuaDatFragment
//import com.citics.cagent.utilities.LevelType
//import com.citics.cagent.utilities.extensions.setListChip
//import com.citics.cagent.utilities.extensions.toShow
//
//class LayoutDFNDViewHienTrangThuaDat @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : LinearLayout(context, attrs, defStyle, defStyleRes) {
//    private var properties: Properties? = null
//    private var binding: LayoutDfNdViewHientrangthuadatBinding
//
//
//    init {
//        binding = LayoutDfNdViewHientrangthuadatBinding.inflate(LayoutInflater.from(context), this)
//
//        attrs?.let {
//            onClick()
//        }
//
//    }
//
//    private fun onClick() {
//
//    }
//
//    fun setData(
//        properties: Properties?,
//        assetLevel: String?
//    ) {
//        this.properties = properties
//        binding.run {
//            properties?.let {
//                vMatTienTiepGiap.setValue(properties?.mat_tien_tiep_giap)
//                vKhoangCachRaDuongChinh.setValue(properties?.khoang_cach_ra_duong_chinh?.toShow())
//                vDoRong.setValue(properties?.do_rong_hem_truoc_nha?.toShow())
//                vKetCauDuong.setValue(properties?.ket_cau_duong)
//                vDoanNhoNhat.setValue(properties?.do_rong_hem_nho_nhat?.toShow())
//                vQuyHoachDuKien.setValue(properties?.quy_hoach_du_kien?.toShow())
//                vDong.setValue(properties?.huong_tiep_giap?.dong)
//                vTay.setValue(properties?.huong_tiep_giap?.tay)
//                vNam.setValue(properties?.huong_tiep_giap?.nam)
//                vBac.setValue(properties?.huong_tiep_giap?.bac)
//                vDongBac.setValue(properties?.huong_tiep_giap?.dong_bac)
//                vDongNam.setValue(properties?.huong_tiep_giap?.dong_nam)
//                vTayBac.setValue(properties?.huong_tiep_giap?.tay_bac)
//                vTayNam.setValue(properties?.huong_tiep_giap?.tay_nam)
//                tvGiaThuyet.setText(properties?.gia_thuyet)
//                binding?.cHienTrangSuDung?.setListChip(
//                    properties?.hien_trang_su_dung?.toMutableList()
//                )
//                binding?.cSoSanhVoiThucTe?.setListChip(
//                    mutableListOf(
//                        properties?.so_sanh_thuc_te_yeu_to_khac ?: ""
//                    )
//                )
//                binding?.cYeuToKhac?.setListChip(
//                    properties?.yeu_to_khac
//                )
//                binding?.tvGhiChu?.text = properties?.ghi_chu_khac_ve_thua_dat
//                binding?.vKetNoiGiaoThong?.setValue(properties?.ket_noi_giao_thong)
//            }
//            handleUiByLevel(assetLevel)
//        }
//    }
//
//    private fun handleUiByLevel(assetLevel: String?) {
//        binding.run {
//            when (assetLevel) {
//                LevelType.TRA_CUU.type -> {
//                    vKetNoiGiaoThong.visibility = GONE
//                    lnHienTrangTiepGiap.visibility = GONE
//                    vHienTrangSuDung.visibility = GONE
//                    vSoSanhVoiThucTe.visibility = GONE
//                    lnGhiChu.visibility = GONE
//                    lnGiaThuyet.visibility = GONE
//                }
//                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
//                    vKetNoiGiaoThong.visibility = GONE
//                    lnHienTrangTiepGiap.visibility = GONE
//                    vHienTrangSuDung.visibility = GONE
//                    vSoSanhVoiThucTe.visibility = GONE
//                    lnGhiChu.visibility = GONE
//                    lnGiaThuyet.visibility = GONE
//                }
//                LevelType.MUA_BAN.type -> {
//                    lnHienTrangTiepGiap.visibility = GONE
//                    vSoSanhVoiThucTe.visibility = GONE
//                    lnGhiChu.visibility = GONE
//                    lnGiaThuyet.visibility = GONE
//                }
//                LevelType.THAM_DINH.type -> {
//
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//                    vKetNoiGiaoThong.visibility = GONE
//                    lnHienTrangTiepGiap.visibility = GONE
//                    vHienTrangSuDung.visibility = GONE
//                    vSoSanhVoiThucTe.visibility = GONE
//                    lnGhiChu.visibility = GONE
//                    lnGiaThuyet.visibility = GONE
//                }
//            }
//        }
//    }
//}
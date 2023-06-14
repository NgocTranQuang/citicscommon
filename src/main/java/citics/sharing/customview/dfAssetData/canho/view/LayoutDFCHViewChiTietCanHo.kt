//package com.citics.cagent.views.dfAssetData.canho.view
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfChViewChitietcanhoBinding
//import com.citics.cagent.databinding.LayoutDfChViewThongtinduanBinding
//import com.citics.cagent.utilities.LevelType
//import com.citics.cagent.utilities.extensions.setListChip
//import com.citics.cagent.utilities.extensions.toShowTime
//import citics.sharing.customview.dfAssetData.nhadat.edit.BaseLayoutDF
//
//class LayoutDFCHViewChiTietCanHo @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {
//
//    private var binding: LayoutDfChViewChitietcanhoBinding
//
//    init {
//        binding = LayoutDfChViewChitietcanhoBinding.inflate(LayoutInflater.from(context), this)
//
//        attrs?.let {
//        }
//    }
//
//    fun setData(properties: Properties?, assetLevel: String?) {
//        binding.run {
//            vMaCanHo.setValue(properties?.ma_can)
//            vloaiCanHo.setValue(properties?.loai_can_ho)
//            vMoTaLoaiCanHo.setValue(properties?.mo_ta)
//            vThap.setValue(properties?.thap)
//            vTang.setValue(properties?.tang)
//            vViTri.setValue(properties?.vi_tri)
//            vDienTichTimTuong.setValue(properties?.dien_tich_tim_tuong?.toString())
//            vDienTichThongThuy.setValue(properties?.dien_tich_thong_thuy?.toString())
//            vHuongNha.setValue(properties?.huong)
//            vHuongCanhQuan.setValue(properties?.canh_quan)
//            vPhongNgu.setValue(properties?.so_phong_ngu?.toString())
//            vPhongVeSinh.setValue(properties?.so_wc?.toString())
//            vPhongKhach.setValue(properties?.so_phong_khach)
//            vPhongBep.setValue(properties?.so_phong_bep)
//            vNoiThat.setValue(properties?.chat_luong_noi_that)
//            vBanCong.setValue(if (properties?.ban_cong?.isNotEmpty() == true) "Có" else "Không")
//            vLoGia.setValue(if (properties?.lo_gia?.isNotEmpty() == true) "Có" else "Không")
//            properties?.yeu_to_khac?.let {
//                chipYeuToKhac.setListChip(it)
//            }
//            properties?.hien_trang_su_dung?.let {
//                chipHienTrangSuDung.setListChip(it.toMutableList())
//            }
//            tvGhiChuKhac.text = properties?.ghi_chu_khac
//            handleUiByLevel(assetLevel)
//
//        }
//    }
//
//    private fun handleUiByLevel(assetLevel: String?) {
//        var listViewGone = mutableListOf<View>()
//        binding.run {
//            when (assetLevel) {
//                LevelType.TRA_CUU.type -> {
//                    listViewGone = mutableListOf(
//                        vMoTaLoaiCanHo,
//                        vHuongNha,
//                        vHuongCanhQuan,
//                        vYeuToKhac,
//                        vHienTrangSuDung,
//                        vGhiChu
//                    )
//                }
//                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
//                    listViewGone =
//                        mutableListOf(vMoTaLoaiCanHo, vNoiThat, vHienTrangSuDung, vGhiChu)
//
//                }
//                LevelType.THAM_DINH.type -> {
//
//                }
//                LevelType.MUA_BAN.type -> {
//                    listViewGone =
//                        mutableListOf(vMoTaLoaiCanHo, vNoiThat, vGhiChu)
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//                    listViewGone =
//                        mutableListOf(vHienTrangSuDung)
//                }
//            }
//        }
//        listViewGone.forEach {
//            it.visibility = GONE
//        }
//    }
//
//}
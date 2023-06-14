//package com.citics.valuation.customview.dfAssetData.canho.edit
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.widget.LinearLayout
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfChViTriBinding
//import com.citics.cagent.utilities.LevelType
//
///**
// * Created by ChinhQT on 25/10/2022.
// */
//
//class LayoutDFCHViTri @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : LinearLayout(context, attrs, defStyle, defStyleRes) {
//
//    private var binding: LayoutDfChViTriBinding
//
//    init {
//        binding = LayoutDfChViTriBinding.inflate(LayoutInflater.from(context), this)
//
//        attrs?.let {
//        }
//    }
//
//    fun setData(properties: Properties?, assetLevel: String?) {
//        val it = properties
//        binding.run {
//
//            vKhuVucTaiSan.setText("${it?.thanh_pho ?: ""} \n ${it?.quan ?: ""} \n ${it?.phuong ?: ""}")
//
//            vSoNha.setText(it?.so_nha)
//            vTenDuong.setText(it?.vi_tri_duong)
//            vKinhDo.setText(it?.kinh_do.toString())
//            vViDo.setText(it?.vi_do.toString())
//            vDuAn.setText(it?.ten_du_an)
//            vChuDauTu.setText(it?.chu_dau_tu)
//            if (it?.link_du_an?.isNotEmpty() == true) {
//                vWebsiteDuAn.setText(it.link_du_an)
//            } else {
//                vWebsiteDuAn.setHint("")
//            }
//
//            vKhuVucTaiSan.disable()
//            vSoNha.disable()
//            vTenDuong.disable()
//            vKinhDo.disable()
//            vViDo.disable()
//            vDuAn.disable()
//            vChuDauTu.disable()
//            vWebsiteDuAn.disable()
//
//            handleUiByLevel(assetLevel)
//        }
//    }
//
//    private fun handleUiByLevel(assetLevel: String?) {
//        binding.run {
//            when (assetLevel) {
//                LevelType.TRA_CUU.type -> {
//
//                }
//                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
//                    vWebsiteDuAn.visibility = GONE
//                    vChuDauTu.visibility = GONE
//                }
//                LevelType.THAM_DINH.type -> {
//
//                }
//                LevelType.MUA_BAN.type -> {
//                    vWebsiteDuAn.visibility = GONE
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//
//                }
//            }
//        }
//    }
//}
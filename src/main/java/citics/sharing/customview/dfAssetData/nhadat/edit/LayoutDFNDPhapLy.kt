//package com.citics.cagent.views.dfAssetData.nhadat.edit
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//
//class LayoutDFNDPhapLy @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {
//    private var binding: LayoutDfNdPhaplyBinding
//    var onGiayToPhapLyClickListener: (() -> Unit)? = null
//    var onTinhTrangPhapLyClickListener: (() -> Unit)? = null
//
//
//    init {
//        binding = LayoutDfNdPhaplyBinding.inflate(LayoutInflater.from(context), this)
//        attrs?.let {
//            onclick()
//        }
//    }
//
//    private fun onclick() {
//        binding.giaytophaply.onClickListener = {
//            onGiayToPhapLyClickListener?.invoke()
//        }
//        binding.tinhtrangphaply.onClickListener = {
//            onTinhTrangPhapLyClickListener?.invoke()
//        }
//        binding.chuSoHuu.onClickListener = {
//
//        }
//    }
//
//    fun handleUiByLevel(assetLevel: String?) {
//        binding.run {
//            when (assetLevel) {
//                LevelType.TRA_CUU.type -> {
//
//                }
//                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
//
//                }
//                LevelType.MUA_BAN.type -> {
//                    chuSoHuu.visibility = GONE
//                }
//                LevelType.THAM_DINH.type -> {
//                    chuSoHuu.visibility = GONE
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//
//                }
//            }
//        }
//    }
//
//}
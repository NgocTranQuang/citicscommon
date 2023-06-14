//package com.citics.cagent.views.dfAssetData.nhadat.edit
//
//import android.content.Context
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import com.citics.cagent.R
//import citics.sharing.data.model.others.SingleChoiceData
//import citics.sharing.data.model.response.AssetDetailData
//import citics.sharing.data.model.response.DetailAdjustmentRates
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfNdThongtingiaodichBinding
//import com.citics.cagent.utilities.*
//import com.citics.cagent.utilities.extensions.toCurrency
//import com.citics.cagent.utilities.extensions.toListChooser
//import com.citics.cagent.utilities.extensions.toShowTime
//import com.citics.cagent.utilities.static_data_utils.StaticDataUtils
//import timber.log.Timber
//import java.text.DecimalFormat
//
//class LayoutDFNDThongTinGiaoDich @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {
//    private var binding: LayoutDfNdThongtingiaodichBinding
//    private val df = DecimalFormat("#,###.##")
//
//    init {
//        binding = LayoutDfNdThongtingiaodichBinding.inflate(LayoutInflater.from(context), this)
//        attrs?.let {
//            onListenrTextChange()
//            onclick()
//        }
//    }
//
//    private fun onListenrTextChange() {
//        binding?.giathuongluongTF?.getTextInput()?.let {
//            it.addTextChangedListener(NumberTextWatcher(it) {
//                refreshTyLeThuongLuong()
//            })
//        }
//
//        binding?.giaraobanTF?.getTextInput()?.let {
//            it.addTextChangedListener(NumberTextWatcher(it) {
//                refreshTyLeThuongLuong()
//            })
//        }
//
////        binding?.giatrinoithatTF?.getTextInput()?.let {
////            it.addTextChangedListener(NumberTextWatcher(it) {})
////        }
//
//        binding?.giaraobanTF?.getTextInput()?.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                val grb = binding?.giaraobanTF?.getText().toString()
//                if (grb.isNotEmpty()) {
//                    properties?.gia_rao_ban = grb.replace(
//                        java.lang.String.valueOf(
//                            df.decimalFormatSymbols.groupingSeparator
//                        ), ""
//                    ).toLong()
//                }
//            }
//
//        })
//        binding?.giathuongluongTF?.getTextInput()?.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                val gtl = binding?.giathuongluongTF?.getText().toString()
//                if (gtl.isNotEmpty()) {
//                    properties?.gia_thuong_luong = gtl.replace(
//                        java.lang.String.valueOf(
//                            df.decimalFormatSymbols.groupingSeparator
//                        ), ""
//                    ).toLong()
//                }
//            }
//
//        })
//
////        binding?.giatrinoithatTF?.getTextInput()?.addTextChangedListener(object : TextWatcher {
////            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
////            }
////
////            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
////            }
////
////            override fun afterTextChanged(s: Editable?) {
////                val gtl = binding?.giatrinoithatTF?.getText().toString()
////                if (gtl.isNotEmpty()) {
////                    properties?.gia_tri_noi_that = gtl.replace(
////                        java.lang.String.valueOf(
////                            df.decimalFormatSymbols.groupingSeparator
////                        ), ""
////                    ).toLong()
////                }
////            }
////
////        })
//    }
//
//    private fun onclick() {
//        binding?.ttgdChooser?.onClickListener = {
//            openSingleChoiceActivity(
//                SingleChoiceData(
//                    title = R.string.ttgd,
//                    lstData = StaticDataUtils.staticNhaDat?.tinh_trang_giao_dich.toListChooser(),
//                    binding?.ttgdChooser?.getText()?.toString()
//                )
//            )
//        }
//
//        binding?.yearBtn?.setOnClickListener {
//            DatePickerUtils.showDatePicker(context, max = getTomorowDate().time) {
//                binding?.yearBtn?.text = it.time.time.toShowTime(FORMAT_DD_MM_YYYY)
//                properties?.thoi_diem_giao_dich = it.time.time
//            }
//        }
//
//        binding?.nguonChooser?.setOnClickListener {
//            openSingleChoiceActivity(
//                SingleChoiceData(
//                    title = R.string.nguon,
//                    lstData = StaticDataUtils.staticFull?.thong_tin_giao_dich.toListChooser(),
//                    binding?.nguonChooser?.getText()?.toString()
//                )
//            )
//        }
//
////        binding?.noithatChooser?.setOnClickListener {
////            openSingleChoiceActivity(
////                SingleChoiceData(
////                    title = R.string.noi_that,
////                    lstData = StaticDataUtils.staticCanHo?.chatLuongNoiThat.toListChooser(),
////                    binding?.noithatChooser?.getText()?.toString()
////                )
////            )
////        }
//
//    }
//
//    fun setData(
//        properties: Properties?, adjustmentRates: DetailAdjustmentRates?, assetLevel: String?
//    ) {
//        this.properties = properties
//        binding?.apply {
//            properties?.tinh_trang_giao_dich?.let { tinh_trang_giao_dich ->
//                ttgdChooser.setText(tinh_trang_giao_dich)
//            }
//
//            properties?.thoi_diem_giao_dich?.let {
//                yearBtn.text = properties.thoi_diem_giao_dich?.toShowTime(FORMAT_DD_MM_YYYY)
//            } ?: kotlin.run {
//                yearBtn.text = context.getString(R.string.ngd)
//            }
//
//            gtqsddTxt.setContent(adjustmentRates?.tong_gia_tri_tham_dinh.toCurrency())
//            giaraobanTF.setText(properties?.gia_rao_ban)
//            giathuongluongTF.setText(properties?.gia_thuong_luong)
//
//            refreshTyLeThuongLuong()
//
//            ttcmapTxt.setContent(properties?.trang_thai_thua_dat?.title ?: "")
//
//            nguonChooser.setText(properties?.nguon ?: "")
////            noithatChooser.setText(properties?.chat_luong_noi_that ?: "")
////            giatrinoithatTF.setText(properties?.gia_tri_noi_that)
//            dotincayTxt.setValue(properties?.do_tin_cay ?: "")
//            dgdtcTxt.setValue(properties?.dien_giai_do_tin_cay ?: "")
//            duongdantaisanTF.setText(properties?.link)
//            nguoilienheTF.setText(properties?.ten_nguoi_lien_he)
//            sodienthoaiTF.setText(properties?.sdt_lien_he)
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
//                    duongdantaisanTF.visibility = GONE
//                    lnNguon.visibility = GONE
//                    tylethuongluongLayout.visibility = GONE
//                    gtqsddTxt.visibility = GONE
//                    lnThongTinKhac.visibility = GONE
//                }
//                LevelType.MUA_BAN.type -> {
//                    duongdantaisanTF.visibility = GONE
//                    lnNguon.visibility = GONE
//                    tylethuongluongLayout.visibility = GONE
//                    gtqsddTxt.visibility = GONE
//                    lnThongTinKhac.visibility = GONE
//                }
//                LevelType.THAM_DINH.type -> {
//
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//
//                }
//            }
//        }
//    }
//
//    private fun refreshTyLeThuongLuong() {
//        try {
//            val percent = binding?.giathuongluongTF?.getText().toString().replace(
//                java.lang.String.valueOf(
//                    df.decimalFormatSymbols.groupingSeparator
//                ), ""
//            ).toLong() * 1.0F / binding?.giaraobanTF?.getText().toString().replace(
//                java.lang.String.valueOf(
//                    df.decimalFormatSymbols.groupingSeparator
//                ), ""
//            ).toLong() * 100
//            binding?.tylethuongluongTxt?.setValue("$percent%")
//        } catch (error: Exception) {
//            binding?.tylethuongluongTxt?.setValue("0%")
//        }
//    }
//
//    override fun onResultSingleChoice(title: Int, id: String?, name: String?) {
//        when (title) {
//            R.string.ttgd -> {
//                Timber.e("setSingleChooser $name")
//                properties?.tinh_trang_giao_dich = name
//                if (name != null) {
//                    binding?.ttgdChooser?.setText(name)
//                }
//            }
//            R.string.nguon -> {
//                properties?.nguon = name
//                if (name != null) {
//                    binding?.nguonChooser?.setText(name)
//                }
//            }
//            R.string.noi_that -> {
//                properties?.chat_luong_noi_that = name
//            }
//        }
//    }
//
//    fun onFieldsValidation(fields: List<String>?) {
//        Timber.d("onFieldsValidation fields $fields")
//
//        if (fields?.contains("thoi_diem_giao_dich") == true) {
//            context.getColor(R.color.color_money)?.let {
//                binding?.yearLayout?.setTextColor(it)
//                binding?.yearBtn?.setTextColor(it)
//            }
//        } else {
//            context.getColor(R.color.color_text)?.let {
//                binding?.yearLayout?.setTextColor(it)
//                binding?.yearBtn?.setTextColor(it)
//            }
//        }
//    }
//
//    fun mergeDataForm(p: AssetDetailData): AssetDetailData {
//        p.properties?.tinh_trang_giao_dich = binding?.ttgdChooser?.getText().toString()
//        // p.properties?.thoi_diem_giao_dich, updated new value when choosing the date
//
//        val grb = binding?.giaraobanTF?.getText().toString()
//        if (grb.isNotEmpty()) {
//            p.properties?.gia_rao_ban = grb.replace(
//                java.lang.String.valueOf(
//                    df.decimalFormatSymbols.groupingSeparator
//                ), ""
//            ).toLong()
//        }
//
//        val gtl = binding?.giathuongluongTF?.getText().toString()
//        if (gtl.isNotEmpty()) {
//            p.properties?.gia_thuong_luong = gtl.replace(
//                java.lang.String.valueOf(
//                    df.decimalFormatSymbols.groupingSeparator
//                ), ""
//            ).toLong()
//        }
//        p.properties?.nguon = binding?.nguonChooser?.getText().toString()
////        p.properties?.chat_luong_noi_that = binding?.noithatChooser?.getText().toString()
////        val giatrinoithat = binding?.giatrinoithatTF?.getText().toString()
////        if (giatrinoithat.isNotEmpty()) {
////            p.properties?.gia_tri_noi_that = giatrinoithat.replace(
////                java.lang.String.valueOf(
////                    df.decimalFormatSymbols.groupingSeparator
////                ), ""
////            ).toLong()
////        }
//        p.properties?.link = binding?.duongdantaisanTF?.getText().toString()
//        p.properties?.ten_nguoi_lien_he = binding?.nguoilienheTF?.getText().toString()
//        p.properties?.sdt_lien_he = binding?.sodienthoaiTF?.getText().toString()
//        return p
//    }
//}
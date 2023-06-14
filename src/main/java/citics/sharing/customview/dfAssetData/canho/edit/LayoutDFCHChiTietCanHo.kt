package citics.sharing.customview.dfAssetData.canho.edit

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import citics.sharing.data.model.response.OptionsSuggestionResponse
import citics.sharing.data.model.response.tham_dinh.Properties
import citics.sharing.customview.dfAssetData.nhadat.edit.BaseLayoutDF
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutDfChChitietcanhoBinding
import com.citics.valuation.data.model.others.ChooserItem
import com.citics.valuation.data.model.others.SelectorItem
import com.citics.valuation.data.model.others.SingleChoiceData
import citics.sharing.extension.getData
import citics.sharing.extension.toListChooser
import citics.sharing.extension.toListSelector
import com.citics.valuation.ui.activity.choice.ChonMaCanHoActivity
import com.citics.valuation.ui.activity.choice.SingleChoiceAndAddingDataActivity
import com.citics.valuation.ui.activity.choice.SingleChoiceAndAddingDataActivity.Companion.LCH_DATA_KEY
import com.citics.valuation.ui.activity.choice.SingleChoiceAndAddingDataActivity.Companion.LCH_TYPE_KEY
import com.citics.valuation.ui.activity.choice.SingleChoiceAndAddingDataActivity.Companion.MCH_TYPE
import com.citics.valuation.ui.activity.choice.SingleChoiceAndAddingDataActivity.Companion.TANG_TYPE
import com.citics.valuation.ui.activity.choice.SingleChoiceAndAddingDataActivity.Companion.THAP_TYPE
import com.citics.valuation.utils.KEY_BUNDLE_DATA
import com.citics.valuation.utils.LIST_HUONG_NHA
import com.citics.valuation.utils.LevelType
import com.citics.valuation.utils.StaticDataUtils

/**
 * Created by ChinhQT on 25/10/2022.
 */

class LayoutDFCHChiTietCanHo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutDfChChitietcanhoBinding
    var onReloadCanHoDetail: ((String, String) -> Unit)? = null
    private var listStaticFilter: List<OptionsSuggestionResponse.Data>? = null
    var startSingleChoiceAndAddingForResult: ActivityResultLauncher<Intent>? = null

    init {
        binding = LayoutDfChChitietcanhoBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            onClick()
        }

    }

    private fun onClick() {
        binding.vMaCanHo.onClickListener = {
            context.startActivity(ChonMaCanHoActivity.newIntent(context, "", ""))
//            findNavController().navigate(
//                R.id.timCanHoFragmentThenBack,
//                TimCanHoThenBackFragment.getArgument(
//                    properties?.ma_du_an
//                        ?: ""
//                )
//            )
        }
        binding.vLoaiCanHo.onClickListener = {
            openSingleChoiceActivity(
                SingleChoiceData(
                    R.string.loai_can_ho,
                    StaticDataUtils.staticCanHo?.loaiCanHo?.toMutableList()?.toListChooser(),
                    binding.vLoaiCanHo.getText()?.toString()
                )
            )
        }
        binding.vThap.onClickListener = {
            goToChooseScreen((listStaticFilter?.map { it.name }
                ?: mutableListOf<String>()).toListChooser(), THAP_TYPE, ChooserItem(
                binding.vThap.getText().toString(), binding.vThap.getText().toString()
            ))
        }
        binding.vTang.onClickListener = {
            val listTang = (listStaticFilter?.firstOrNull {
                it.name == binding.vThap.getText().toString()
            }?.items?.map { it.name } ?: mutableListOf()).toListChooser()

            goToChooseScreen(
                listTang, TANG_TYPE, ChooserItem(
                    binding.vTang.getText().toString(), binding.vTang.getText().toString()
                )
            )
        }
        binding.vHuongNha.onClickListener = {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.huong_nha,
                    LIST_HUONG_NHA,
                    properties?.huong
                )
            )
        }

        binding.vViTriCanHo.setOnClickListener {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.vi_tri_can_ho,
                    StaticDataUtils.staticCanHo?.viTriCanHo.toListChooser(),
                    binding.vViTriCanHo.getText()?.toString()
                )
            )
        }
        binding.vNoiThat.setOnClickListener {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.noi_that,
                    lstData = StaticDataUtils.staticCanHo?.chatLuongNoiThat?.toListChooser(),
                    binding.vNoiThat.getText()?.toString()
                )
            )
        }

        binding.vBanCong.setOnClickListener {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.ban_cong,
                    StaticDataUtils.staticCanHo?.banCong?.toListChooser(),
                    binding.vBanCong.getText().toString()
                )
            )
        }

        binding.vLoGia.setOnClickListener {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.lo_gia,
                    StaticDataUtils.staticCanHo?.loGia?.toListChooser(),
                    binding.vLoGia.getText().toString()
                )
            )
        }
        binding.vYeuToKhac.setListChoice(
            StaticDataUtils.staticCanHo?.yeuToKhac?.toListSelector() ?: mutableListOf()
        )
        binding.vHienTrangSuDung?.setListChoice(StaticDataUtils.staticFull?.hien_trang_su_dung?.map { it.title }
            ?.toListSelector() ?: mutableListOf())
    }

    override fun setLauncher(
        singleChoiceLauncher: ActivityResultLauncher<Intent>?,
        multiChoiceLauncher: ActivityResultLauncher<Intent>?,
    ) {
        super.setLauncher(singleChoiceLauncher, multiChoiceLauncher)
        binding.vYeuToKhac.setLauncher(null, startMultiChoiceForResult)
        binding.vHienTrangSuDung.setLauncher(null, startMultiChoiceForResult)
    }

    private fun goToChooseScreen(
        toTypedArray: List<ChooserItem>, type: Int, chooserItem: ChooserItem
    ) {
        startSingleChoiceAndAddingForResult?.launch(
            SingleChoiceAndAddingDataActivity.newIntent(
                context,
                SingleChoiceData(title = 0, lstData = toTypedArray, selected = null),
                chooserItem = chooserItem,
                type = type
            )
        )
    }


    fun setData(properties: Properties?, assetLevel: String?) {
        this.properties = properties
        binding.run {
            vMaCanHo.setText(properties?.ma_can ?: "")
            vLoaiCanHo.setText(properties?.loai_can_ho ?: "")
            vMoTaLoaiCanHo.setText(properties?.mo_ta_loai_can_ho) {
                properties?.mo_ta_loai_can_ho = it
            }
            vThap.setText(properties?.thap ?: "")
            vTang.setText(properties?.tang ?: "")
            vViTriCanHo.setText(properties?.vi_tri_can_goc ?: "")
            vDienTichTimTuong.setText(properties?.dien_tich_tim_tuong) {
                properties?.dien_tich_tim_tuong = it
            }
            vDienTichThongThuy.setText(properties?.dien_tich_thong_thuy) {
                properties?.dien_tich_thong_thuy = it
            }
            vHuongNha.setText(properties?.huong ?: "")
            vHuongCanhQuan.setText(properties?.canh_quan) {
                properties?.canh_quan = it
            }
            vPhongNgu.setText(properties?.so_phong_ngu) {
                properties?.so_phong_ngu = it
            }
            vPhongVeSinh.setText(properties?.so_wc) {
                properties?.so_wc = it
            }
            vPhongKhach.setText(properties?.so_phong_khach) {
                properties?.so_phong_khach = it
            }
            vPhongBep.setText(properties?.so_phong_bep) {
                properties?.so_phong_bep = it
            }
            vNoiThat.setText(properties?.chat_luong_noi_that ?: "")

            vBanCong.setText(properties?.ban_cong ?: "")
            vLoGia.setText(properties?.lo_gia ?: "")


            properties?.listYeuToKhacTotal?.let {
                vYeuToKhac.setListSelected(it.toListSelector())
            }
            properties?.hien_trang_su_dung?.let {
                vHienTrangSuDung.setListSelected(it.toListSelector())
            }

            vGhiChu.setText(properties?.ghi_chu_khac) {
                properties?.ghi_chu_khac = it
            }
            handleUiByLevel(assetLevel)
        }
    }

    fun setListCanHoByDuAn(listStaticFilter: List<OptionsSuggestionResponse.Data>? = null) {
        this.listStaticFilter = listStaticFilter
    }

    override fun onResultMultiChoice(title: Int, list: MutableList<SelectorItem>) {
        when (title) {
            R.string.yeu_to_khac -> {
                val itemKhacKhac = list.firstOrNull { it.isCustomData }
                properties?.yeu_to_khac_khac =
                    itemKhacKhac?.name
                val itemSelectedWithouKhacKhac =
                    list.filter { !it.isCustomData }

                properties?.yeu_to_khac =
                    itemSelectedWithouKhacKhac.map { it.name }.toMutableList()
                binding.vYeuToKhac.setListSelected(
                    properties?.listYeuToKhacTotal?.toListSelector() ?: mutableListOf()
                )
            }
            R.string.hien_trang_su_dung -> {
                properties?.hien_trang_su_dung =
                    list.map { it.name }.toMutableList()
                binding.vHienTrangSuDung.setListSelected(properties?.hien_trang_su_dung.toListSelector())

            }
        }
    }

    override fun onResultSingleChoice(title: Int, id: String?, name: String?) {
        when (title) {
            R.string.loai_can_ho -> {
                onResultLoaiCanHo(name)
            }
            R.string.huong_canh_quan -> {
                onResultHuongCanhQuan(name)
            }
            R.string.vi_tri_can_ho -> {
                properties?.vi_tri_can_goc = name
                binding.vViTriCanHo.setText(name ?: "")
            }
            R.string.noi_that -> {
                properties?.chat_luong_noi_that = name
                binding.vNoiThat.setText(name ?: "")

            }
            R.string.ban_cong -> {
                properties?.ban_cong = name
                binding.vBanCong.setText(name ?: "")

            }
            R.string.lo_gia -> {
                properties?.lo_gia = name
                binding.vLoGia.setText(name ?: "")

            }
            R.string.huong_nha -> {
                onResultHuongNha(name)
            }
        }
    }


    private fun onResultHuongCanhQuan(name: String?) {
        binding.vHuongCanhQuan.setText(name ?: "")
        properties?.canh_quan = name
    }

    private fun onResultLoaiCanHo(name: String?) {
        properties?.loai_can_ho = name
        binding.vLoaiCanHo.setText(name ?: "")
    }


    fun onResultMaCanHo(item: ChooserItem?) {
        binding.vMaCanHo.setText(item?.name ?: "")
        properties?.ma_can = item?.name
        if (item?.isCustomData == false) {
            onReloadCanHoDetail?.invoke(properties?.ma_du_an ?: "", item?.name ?: "")
        }
    }

    private fun onResultHuongNha(name: String?) {
        properties?.huong = name
        binding?.vHuongNha?.setText(name ?: "")
    }

    private fun onResultTang(name: String?) {
        if (properties?.tang == name) {
            return
        }
        properties?.tang = name
        binding?.vTang?.setText(name ?: "")

        properties?.huong = ""
        binding?.vHuongNha?.setText("")
    }

    private fun onResultThap(name: String?) {
        if (properties?.thap == name) {
            return
        }
        properties?.thap = name
        binding?.vThap?.setText(name ?: "")

        properties?.tang = ""
        binding?.vTang?.setText("")

        properties?.huong = ""
        binding?.vHuongNha?.setText("")
    }

    private fun handleUiByLevel(assetLevel: String?) {

        var listViewGone = mutableListOf<View>()
        binding.run {
            when (assetLevel) {
                LevelType.TRA_CUU.type -> {
                    listViewGone = mutableListOf(
                        vMaCanHo,
                        vMoTaLoaiCanHo,
                        vHuongNha,
                        vHuongCanhQuan,
                        vYeuToKhac,
                        vHienTrangSuDung,
                        vGhiChu
                    )
                }
                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
                    listViewGone =
                        mutableListOf(vMoTaLoaiCanHo, vNoiThat, vHienTrangSuDung, vGhiChu)

                }
                LevelType.THAM_DINH.type -> {

                }
                LevelType.MUA_BAN.type -> {
                    listViewGone =
                        mutableListOf(vMoTaLoaiCanHo, vNoiThat, vGhiChu)
                }
                LevelType.TAI_SAN_SO_SANH.type -> {
                    listViewGone =
                        mutableListOf(vHienTrangSuDung)
                }
            }
        }
        listViewGone.forEach {
            it.visibility = GONE
        }
        binding.lnContent.visibility = View.VISIBLE
    }

    fun setDataSingleChoiceAndAddingCallBack(intent: Intent?) {
        val bundle = intent?.getBundleExtra(KEY_BUNDLE_DATA)
        val itemSelected = bundle?.getData<ChooserItem>(LCH_DATA_KEY)
        val typeChooser = bundle?.getInt(LCH_TYPE_KEY)
        when (typeChooser) {
            THAP_TYPE -> {
                onResultThap(itemSelected?.name)
            }
            TANG_TYPE -> {
                onResultTang(itemSelected?.name)
            }
            MCH_TYPE -> {
                onResultMaCanHo(itemSelected)
            }
        }
    }
}
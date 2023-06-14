package citics.sharing.customview.dfAssetData.nhadat.edit

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import citics.sharing.data.model.response.DetailUsingPurpose
import citics.sharing.data.model.response.DonGiaUBNDResponse
import citics.sharing.data.model.response.LandDTO
import com.citics.valuation.data.model.response.RequiredLand
import citics.sharing.data.model.response.tham_dinh.Properties
import citics.sharing.data.model.response.tham_dinh.RecordResponse
import com.citics.cagent.ui.activity.choice.SingleChoiceTHSDDActivity
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutDfNdChitietthuadatBinding
import com.citics.valuation.adapter.tracuu.DonGiaDatOAdapter
import com.citics.valuation.adapter.tracuu.LandAdapter
import com.citics.valuation.adapter.tracuu.MucDichSuDungDatAdapter
import com.citics.valuation.data.model.others.ChooserItem
import com.citics.valuation.data.model.others.MultiChoiceData
import com.citics.valuation.data.model.others.SelectorItem
import com.citics.valuation.data.model.others.SingleChoiceData
import citics.sharing.extension.addListChooser
import citics.sharing.extension.setListChip
import citics.sharing.extension.toListChooser
import citics.sharing.extension.toShow
import com.citics.valuation.utils.*
import timber.log.Timber

/**
 * Created by ChinhQT on 25/10/2022.
 */

class LayoutDFNDChiTietThuaDat @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {

    var onUpdateHuong: ((String?) -> Unit)? = null
    var onChangeMDSDD: ((MutableList<DetailUsingPurpose>?) -> Unit)? = null
    private var donGiaUBND: DonGiaUBNDResponse? = null
    private var lstUsingPurpose: MutableList<DetailUsingPurpose>? = null
    private var binding: LayoutDfNdChitietthuadatBinding
    private var adapterMucDichSuDungDat: MucDichSuDungDatAdapter? = null
    private var landAdapter: LandAdapter? = null
    private var usingDetailClicked: DetailUsingPurpose? = null
    private var adapterDonGiaThuaDat: DonGiaDatOAdapter? = null

    /*Sẽ có 1 vài logic mà bên tra cứu sẽ khác với phần còn lại nên sẽ có biến này để control*/
    private var isInTraCuu: Boolean = false

    var onUpdateMDSDD: ((List<DetailUsingPurpose>) -> Unit)? = null

    init {
        binding = LayoutDfNdChitietthuadatBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {


        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        adapterMucDichSuDungDat = MucDichSuDungDatAdapter(
            context, mutableListOf(), true
        )
        binding.rvMucDichSuDungDat.adapter = adapterMucDichSuDungDat
        adapterMucDichSuDungDat?.onClickChonThoiHan = {
            usingDetailClicked = it
            startSingleChoiceForResult?.launch(
                SingleChoiceTHSDDActivity.newIntent(
                    context,
                    SingleChoiceData(
                        title = R.string.chon_thoi_han,
                        UsingTimeType.values().map {
                            ChooserItem(
                                id = it.type, name = it.type
                            )
                        }.toMutableList(),
                        it.using_time_type
                    )
                )
            )
        }
        landAdapter = LandAdapter(context, mutableListOf())
        binding.rvLand.adapter = landAdapter
        landAdapter?.onChange = {
            tinhTongDienTichDat()
        }
        adapterDonGiaThuaDat = DonGiaDatOAdapter(context, mutableListOf())
        binding.rvDonGia.adapter = adapterDonGiaThuaDat
        onclick()
    }

    private fun onclick() {
        binding.vHuongNha.setOnClickListener {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.huong_nha,
                    lstData = StaticDataUtils.staticNhaDat?.danhMucLand?.huongChinh?.toListChooser(),
                    selected = binding.vHuongNha.getText().toString()
                )
            )
        }
        binding.cMucDichSuDungDat.setOnClickListener {
            val lstMDSDDStatic = StaticDataUtils.staticMDSDD?.map { item ->
                ChooserItem(
                    id = item.kind ?: "",
                    name = item.name ?: ""
                )
            } ?: listOf()
            Timber.d("ID Multichoice cMucDichSuDungDat R.string.muc_dich_su_dung_dat: ${R.string.muc_dich_su_dung_dat}")
            openMultiChoiceActivity(
                MultiChoiceData(
                    title = R.string.muc_dich_su_dung_dat,
                    lstData = lstMDSDDStatic,
                    selected = adapterMucDichSuDungDat?.mainList?.map { it.title ?: it.name ?: "" }
                        ?.toMutableList()
                )
            )
        }

        binding.vHinhDang.setOnClickListener {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.hinh_dang,
                    StaticDataUtils.staticNhaDat?.danhMucLand?.hinhDang.toListChooser(),
                    binding.vHinhDang.getText().toString()
                )
            )
        }

        binding.vHinhThucSuDung.setOnClickListener {
            val lstHTSD = HinhThucSuDung.values()
                .map {
                    ChooserItem(
                        id = context.getString(it.title),
                        name = context.getString(it.title)
                    )
                }
                .toMutableList()
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.hinh_thuc_su_dung,
                    lstHTSD,
                    binding.vHinhThucSuDung.getText().toString()
                )
            )
        }

        binding.ubndChooser.onClickListener = {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.vttubnd,
                    donGiaUBND?.vt_list?.map {
                        ChooserItem(
                            id = it.vi_tri_theo_ubnd ?: UNKNOWN,
                            name = it.vi_tri_theo_ubnd ?: UNKNOWN
                        )
                    }?.toMutableList() ?: mutableListOf(),
                    binding.ubndChooser.getText().toString()
                )
            )
        }
    }

    fun setDonGiaUBND(donGiaUBND: DonGiaUBNDResponse?) {
        this.donGiaUBND = donGiaUBND
    }

    fun setData(
        properties: Properties?,
        usingPurpose: MutableList<DetailUsingPurpose>?,
        assetLevel: String?
    ) {
        this.properties = properties
        this.lstUsingPurpose = usingPurpose
        handleUiByLevel(assetLevel)
    }


    @SuppressLint("SetTextI18n")
    fun tinhTongDienTichDat() {
        properties?.let { properties ->
            val total = properties.lands?.map { it.dien_tich_dat }
                ?.reduce { acc, fl -> (acc ?: 0f) + (fl ?: 0f) }
            binding.vDienTichDuocCongNhan.setValue(total.toShow())
        }
    }

    override fun onResultSingleChoice(title: Int, id: String?, name: String?) {
        when (title) {
            R.string.hinh_dang -> {
                properties?.hinh_dang = name
                binding.vHinhDang.setText(name ?: "")
            }
            R.string.huong_nha -> {
                properties?.huong = name
                binding.vHuongNha.setText(name ?: "")
                onUpdateHuong?.invoke(name)
            }
            R.string.hinh_thuc_su_dung -> {
                properties?.hinh_thuc_su_dung = name
                binding.vHinhThucSuDung.setText(name ?: "")
            }

            R.string.vttubnd -> {
                binding.ubndChooser.setText(name ?: "")
                properties?.vi_tri_theo_ubnd = name
                donGiaUBND?.vt_list?.firstOrNull { it.vi_tri_theo_ubnd == name }
                    ?.let { donGia ->
                        val listDonGia = donGia.item_list
                        properties?.lands?.forEach { land ->
                            listDonGia?.firstOrNull { it.using_purpose == land.name }?.let {
                                land.don_gia_ubnd = it.don_gia_ubnd
                            }
                        }
                    }
                adapterDonGiaThuaDat?.mainList = properties?.lands
            }
        }
    }

    override fun onResultSingleChoiceUsingTimeType(
        name: String?,
        nam: String?,
        ngayThang: String?
    ) {
        usingDetailClicked?.using_time_type = name
        usingDetailClicked?.day_month = ngayThang
        usingDetailClicked?.year = nam
        onUpdateMDSDD?.invoke(adapterMucDichSuDungDat?.mainList ?: mutableListOf())
    }

    override fun onResultMultiChoice(title: Int, list: MutableList<SelectorItem>) {
        Timber.d("ID Multichoice onResultMultiChoice: ${title}")
        Timber.d("ID Multichoice R.string.muc_dich_su_dung_dat: ${R.string.muc_dich_su_dung_dat}")

        when (title) {
            R.string.muc_dich_su_dung_dat -> {

                // Dành cho tra cứu
                if (isInTraCuu) {
                    val newLstUsingPurpose = list.map {
                        DetailUsingPurpose().apply {
                            this.title = it.name
                            this.name = it.name
                            this.kind = it.id
                        }
                    }.toMutableList()
                    lstUsingPurpose?.clear()
                    lstUsingPurpose?.addAll(newLstUsingPurpose)
                    properties?.lands.addListChooser(list)

                    binding.chipGroup.setListChip(
                        lstUsingPurpose?.toMutableList()?.map { it.name ?: "" }
                            ?.toMutableList())
                    adapterMucDichSuDungDat?.mainList = lstUsingPurpose?.toMutableList()
                    landAdapter?.mainList = properties?.lands?.toMutableList()
                    onChangeMDSDD?.invoke(lstUsingPurpose)
                } else {

                    // Dành cho ngoài tra cứu
                    val listMDSDDSelected =
                        list.map { itemSelected -> StaticDataUtils.staticMDSDD?.firstOrNull { it.kind == itemSelected.id } }
                            .toMutableList()

                    listMDSDDSelected.forEach { itemSlected ->
                        val item =
                            adapterMucDichSuDungDat?.mainList?.firstOrNull { it.name == itemSlected?.name }
                        if (item != null) {
                            // Thằng mục đích này có trong adapter => Gán lại thời hạn sử dụng
                            itemSlected?.using_time_type = item.using_time_type
                            itemSlected?.day_month = item.day_month
                            itemSlected?.year = item.year
                        }
                    }
                    onUpdateMDSDD?.invoke(listMDSDDSelected.filterNotNull())
                }

            }
        }
    }

    fun updateMDSSD(it: RecordResponse?) {
        /*Trường hợp đang điền dở thông tin diện tích thửa đất mà chưa bấm nút cập nhật, sau đó user add thêm mục đích sử dụng đất => request api
        và trả về list lands. List land mà server trả về này sẽ không có thông số mà mình đang điền dang dở. Mình phải tự add thông số đó
        bằng tay trở lại*/
        val listMDSDDSelected =
            it?.data?.assessed?.usingPurpose?.toMutableList() ?: mutableListOf()
        val newListLand = mutableListOf<LandDTO>()
        listMDSDDSelected.forEach { up ->
            val item =
                properties?.lands?.firstOrNull { it.name == up.title }
            if (item == null) {
                newListLand.add(LandDTO(name = up.title, kind = up.kind))
            } else {
                newListLand.add(item.copy())
            }
        }
        it?.data?.assessed?.properties?.lands = newListLand.toMutableList()
        setData(
            it?.data?.assessed?.properties,
            it?.data?.assessed?.usingPurpose,
            it?.data?.assessed?.level
        )
//        setValidationLand(it.extra.validation_result.asset.)
    }

    private fun handleUiByLevel(assetLevel: String?) {
        isInTraCuu = false
        binding.run {
            when (assetLevel) {
                LevelType.TRA_CUU.type -> {
                    isInTraCuu = true
                    rvMucDichSuDungDat.visibility = GONE
                    vDienTichDuocCongNhan.visibility = GONE
                    vHinhThucSuDung.visibility = GONE
                    vLineBig.visibility = GONE
                    lnChuyenDoiMucDich.visibility = GONE
                    vHeSoK.visibility = GONE
                    lnDonGiaTheoUbnd.visibility = GONE

                }
                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
                    rvMucDichSuDungDat.visibility = GONE
                    vDienTichDuocCongNhan.visibility = GONE
                    vHinhThucSuDung.visibility = GONE
                    vLineBig.visibility = GONE
                    lnChuyenDoiMucDich.visibility = GONE
                    vHeSoK.visibility = GONE
                    lnDonGiaTheoUbnd.visibility = GONE
                }
                LevelType.MUA_BAN.type -> {
                    chipGroup.visibility = GONE
                    vDienTichDuocCongNhan.visibility = GONE
                    vHinhThucSuDung.visibility = GONE
                    vLineBig.visibility = GONE
                    lnChuyenDoiMucDich.visibility = GONE
                    vHeSoK.visibility = GONE
                    lnDonGiaTheoUbnd.visibility = GONE

                }
                LevelType.THAM_DINH.type -> {
                    chipGroup.visibility = GONE

                }
                LevelType.TAI_SAN_SO_SANH.type -> {
                    chipGroup.visibility = GONE
                    vHinhThucSuDung.visibility = GONE
                    vHmcd.visibility = GONE
                    vHeSoK.visibility = GONE
                }
            }
        }
        setDataForView()
    }

    private fun setDataForView() {
        binding.run {
            properties?.let {
                vDienTichDuocCongNhan.setValue(it.dien_tich_dc_cong_nhan?.toShow())
                vDTKDCN.setText(it.dien_tich_khong_dc_cong_nhan) { value ->
                    it.dien_tich_khong_dc_cong_nhan = value
                }
                vChieuDai.setText(it.chieu_dai?.toShow()) { value ->
                    it.chieu_dai = value?.toFloatOrNull()
                }
                vChieuRong.setText(it.chieu_rong?.toShow()) { value ->
                    it.chieu_rong = value?.toFloatOrNull()
                }
                vHinhDang.setText(it.hinh_dang ?: "")
                vHuongNha.setText(it.huong ?: "")
                onUpdateHuong?.invoke(vHuongNha.getText()?.toString())
                vHmcd.setValue(it.k_factor_upper_limit)
                vHeSoK.setText(it.k_factor_upper_limit) { value ->
                    it.k_factor_upper_limit = value
                }

                binding.vHmcd.setValue(it.k_factor_upper_limit)
                binding.ubndChooser.setText(it.vi_tri_theo_ubnd ?: "")
                vHinhThucSuDung.setText(it.hinh_thuc_su_dung ?: "")
                binding.chipGroup.setListChip(
                    lstUsingPurpose?.toMutableList()?.map { it.name ?: "" }
                        ?.toMutableList())
                adapterMucDichSuDungDat?.mainList = lstUsingPurpose?.toMutableList()
                landAdapter?.mainList = it.lands?.toMutableList()
                adapterDonGiaThuaDat?.mainList = it.lands?.toMutableList()

            }
            binding.lnContent.visibility = View.VISIBLE
        }
    }

    fun setValidationLand(it: List<RequiredLand>?) {
        (binding.rvLand.adapter as? LandAdapter)?.checkValidation(it)
        adapterMucDichSuDungDat?.checkValidation(it)
    }

    fun setOnChangeUsingPurpose(onChangeUsingPurpose: (List<DetailUsingPurpose>?) -> Unit) {
        this.onChangeMDSDD = onChangeUsingPurpose
    }
}
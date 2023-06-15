package citics.sharing.data.model.others

import citics.sharing.data.model.response.tham_dinh.RecordResponse

class DanhSachThanhVienDTO(val owner: RecordResponse.RecordData.RecordOwner) {
    var title: String? = null
    var showBottomDivider: Boolean = false
}
package citics.sharing.utils

import citics.sharing.data.model.response.DetailUsingPurpose
import citics.sharing.data.model.response.StaticCanHoResponse
import citics.sharing.data.model.response.StaticNhaDatResponse
import citics.sharing.data.model.response.TemplateResponse


object StaticDataUtils {
    // Data static api
    var staticNhaDat: StaticNhaDatResponse.Data? = null
    var staticCanHo: StaticCanHoResponse.Data? = null
    var staticFull: TemplateResponse.DataResponse? = null
    var staticMDSDD: List<DetailUsingPurpose>? = null

    var listLegalStaticNhaDat: List<String> = mutableListOf()
    var listLegalStaticCanHo: List<String> = mutableListOf()

}
package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BarBankResponse(val bank_id: String? = null, val bank_name: String? = null) : Parcelable
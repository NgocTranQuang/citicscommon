package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import citics.sharing.data.model.response.AssetDetailData
import citics.sharing.data.model.response.Document
import com.citics.valuation.data.model.response.StepsValidationResult
import citics.sharing.data.model.response.base.BaseResponse
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Created by ChinhQT on 14/12/2022.
 */
@Parcelize
open class RecordResponse : BaseResponse<RecordResponse.RecordData, RecordResponse.RecordExtra>(),
    Parcelable {

    data class RecordExtra(
        @Json(name = "vi_pham_tsss") val viPhamTsss: ViPhamTsss? = null,
        @Json(name = "validation_result") val validation_result: Validation? = null
    ) {
        data class ViPhamTsss(
            @Json(name = "inx_id") val inx_id: Int,
            @Json(name = "id") val id: String? = null,
            @Json(name = "violations") val violations: List<String>
        )

        data class Validation(
            @Json(name = "asset") var asset: StepsValidationResult? = null,
            @Json(name = "compared_assets") var compared_assets: List<StepsValidationResult>? = null
        )
    }

    @Parcelize
    data class RecordData(
        @Json(name = "id") val id: String,
        @Json(name = "contract_id") val contractId: String? = null,
        @Json(name = "previous_id") val previousId: String? = null,
        @Json(name = "chat_id") val chatId: String? = null,
        @Json(name = "dia_chi1") val address1: String? = null,
        @Json(name = "dia_chi2") val address2: String? = null,
        @Json(name = "property_type") val propertyType: PropertyType?=null,
        @Json(name = "price") val price: RecordPrice? = null,
        @Json(name = "status") val status: RecordStatus? = null,
        @Json(name = "survey_time") val surveyTime: SurveyTime? = null,
        @Json(name = "execution_time") val executionTime: ExecutionTime? = null,
        @Json(name = "execution_times") val executionTimes: List<ExecutionTimes>? = null,
        @Json(name = "tasks") val tasks: List<Task>? = null,
        @Json(name = "active") val active: RecordActive? = null,
        @Json(name = "type") val type: String? = null,
        @Json(name = "created_date") val createdDate: String? = null,
        @Json(name = "last_modififed_date") val lastModified: Long? = null,
        @Json(name = "owners") val owners: List<RecordOwner>? = null,
        @Json(name = "assessed") var assessed: AssetDetailData? = null,
        @Json(name = "partner_template") var partnerTemplate: RecordOwner.Group? = null,
        @Json(name = "method_landing") val medthodLanding: MethodLanding? = null,
        @Json(name = "guarantee") val guarantee: Guarantee? = null,
        @Json(name = "violation") val violation: Violation? = null,
        @Json(name = "passed_guarantee") val passedGuarantee: Boolean? = null,
        @Json(name = "required_violation") val requiredViolation: Boolean? = null,
        @Json(name = "badge_violation") val badgeViolation: Int? = null,
        @Json(name = "badge_guarantee") val badgeGuarantee: Int? = null,
        @Json(name = "need_explanation") val needExplanation: Boolean? = null,
        @Json(name = "extra") val extra: Extra? = null,
        @Json(name = "records") val records: List<RecordData>? = null, // Dành cho Contract
        @Json(name = "customer") val customer: Customer? = null,
        @Json(name = "report") var report: List<Report>? = null,
        @Json(name = "items") val items: List<Item>? = null,
        @Json(name = "other_asset") val otherAsset: AssetDetailData? = null,
        @Json(name = "record_letter") val recordLetter: RecordLetter? = null,
        @Json(name = "legals") val legals: Legals? = null,
        @Json(name = "safe_guarantee") var safeGuarantee: Boolean? = null,
        @Json(name = "vi_pham_tsss") var vi_pham_tsss: Boolean? = false,
        @Json(name = "take_invoice") var take_invoice: Boolean? = false,
        @Json(name = "shipper_address") val shipper_address: ShipperAddress? = null
    ) : Parcelable {
        @Parcelize
        data class RecordLetter(
            @Json(name = "note") val note: String? = null,
            @Json(name = "assessed_name") val assessedName: String? = null,
            @Json(name = "assessed_purpose") val assessedPurpose: RecordLetterKeyVal? = null,
            @Json(name = "assessed_purpose_other") val assessedPurposeOther: String? = null,
            @Json(name = "assessed_time") val assessedTime: String? = null,
            @Json(name = "printed_time") val printedTime: String? = null,
            @Json(name = "released_date") val releasedDate: String? = null,
            @Json(name = "base_value") val baseValue: RecordLetterKeyVal? = null,
            @Json(name = "assessed_methods") val assessedMethods: List<RecordLetterKeyVal>? = null,
        ) : Parcelable {
            @Parcelize
            data class RecordLetterKeyVal(
                @Json(name = "key") val key: String,
                @Json(name = "title") val title: String,
            ) : Parcelable
        }

        @Parcelize
        data class MethodLanding(
            @Json(name = "key") val key: String,
            @Json(name = "title") val title: String,
        ) : Parcelable

        @Parcelize
        data class Item(
            @Json(name = "item_quantity") val itemQuantity: Float,
            @Json(name = "item_unit_price") val itemUnitPrice: Long,
            @Json(name = "item_name") val itemName: String,
            @Json(name = "item_unit") val itemUnit: String,
            @Json(name = "item_quality_remaining") val itemQualityRemaining: String,
            @Json(name = "item_price") val itemPrice: Long,
            @Json(name = "item_asset_type") val itemAssetType: ItemAssetType,
        ) : Parcelable {
            @Parcelize
            data class ItemAssetType(
                @Json(name = "label") val label: String,
                @Json(name = "value") val value: String,
            ) : Parcelable
        }

        @Parcelize
        data class Report(
            @Json(name = "category") val reportCate: ReportCategory? = null,
            @Json(name = "files") var files: List<Document>? = null,
            @Json(name = "date") val date: Long? = null,
        ) : Parcelable {
            @Parcelize
            data class ReportCategory(
                @Json(name = "title") val title: String?,
                @Json(name = "key") val key: String?,
            ) : Parcelable
        }

        @Parcelize
        data class Customer(
            @Json(name = "type") val type: String,
            @Json(name = "fullName") val fullname: String?,
            @Json(name = "dia_chi") val address: String?,
            @Json(name = "phoneNo") val phoneNo: String?,
            @Json(name = "idPersonal") val idPersonal: String?,
            @Json(name = "appliedDate") val appliedDate: String?,
            @Json(name = "appliedPlace") val appliedPlace: String?,
            @Json(name = "ma_thanh_pho") val cityCode: String?,
            @Json(name = "thanh_pho") val cityName: String?,
            @Json(name = "ma_quan") val districtCode: String?,
            @Json(name = "quan") val districtName: String?,
            @Json(name = "ma_phuong") val wardCode: String?,
            @Json(name = "phuong") val wardName: String?,
            @Json(name = "email") val email: String? = null,
            @Json(name = "identityCards") val identityCards: List<Photo>? = null,
            @Json(name = "business") val business: CorpCustomer? = null,
        ) : Parcelable {
            @Parcelize
            data class CorpCustomer(
                @Json(name = "taxCode") val taxCode: String? = null,
                @Json(name = "deputyName") val deputyName: String? = null,
                @Json(name = "deputyPosition") val deputyPosition: String? = null,
                @Json(name = "authorizationLetter") val authorizationLetter: String? = null,
                @Json(name = "guideManName") val guideManName: String? = null,
                @Json(name = "guideManPhone") val guideManPhone: String? = null,
            ) : Parcelable

        }

        @Parcelize
        data class Extra(
            @Json(name = "is_scan_asset") val isScanAsset: Boolean? = null
        ) : Parcelable

        @Parcelize
        data class Guarantee(
            @Json(name = "conditions") val conditions: List<Condition>? = null,
        ) : Parcelable {
            @Parcelize
            data class Condition(
                @Json(name = "another") val another: Boolean? = null,
                @Json(name = "satisfied") val satisfied: Boolean? = null,
                @Json(name = "factor_id") val factorId: String? = null,
                @Json(name = "factor_name") val factorName: String? = null,
                @Json(name = "factor_title") val factorTitle: String? = null,
                @Json(name = "condition_value") val conditionValue: String? = null,
                @Json(name = "record_value") val recordValue: String? = null,
                @Json(name = "is_satisfied") val isSatisfied: Boolean? = null,
                @Json(name = "is_another") val isAnother: Boolean? = null,
            ) : Parcelable

        }

        @Parcelize
        data class Violation(
            @Json(name = "violations") val violations: List<ViolationItem>,
        ) : Parcelable {
            @Parcelize
            data class ViolationItem(
                @Json(name = "category") val category: ViolationCategory,
                @Json(name = "reasons") val reasons: List<ViolationReason>,
                @Json(name = "status") val status: ViolationStatus,
                @Json(name = "value") val value: ViolationValue,
                @Json(name = "violation_id") val violationId: String,
                @Json(name = "category_name") val categoryName: String,
                @Json(name = "created_date") val createdDate: Long,
            ) : Parcelable {
                @Parcelize
                data class ViolationCategory(
                    @Json(name = "id") val id: String,
                    @Json(name = "title") val title: String,
                ) : Parcelable

                @Parcelize
                data class ViolationReason(
                    @Json(name = "id") val id: String,
                    @Json(name = "title") val title: String,
                ) : Parcelable

                @Parcelize
                data class ViolationStatus(
                    @Json(name = "index") val index: String,
                    @Json(name = "status") val status: String,
                    @Json(name = "step") val step: String,
                    @Json(name = "status_title") val title: String,
                    @Json(name = "color") val color: String,
                ) : Parcelable

                @Parcelize
                data class ViolationValue(
                    @Json(name = "src") val src: ViolationValueSrcDes,
                    @Json(name = "des") val des: ViolationValueSrcDes,
                    @Json(name = "standard") val standard: Long,
                    @Json(name = "value") val value: Float,
                    @Json(name = "violated") val violated: Boolean,
                    @Json(name = "message") val message: String,
                    @Json(name = "category") val category: String,
                    @Json(name = "action") val action: String,
                ) : Parcelable {
                    @Parcelize
                    data class ViolationValueSrcDes(
                        @Json(name = "key") val key: String? = null,
                        @Json(name = "value") val value: Long,
                        @Json(name = "rate") val rate: Float? = null,
                    ) : Parcelable

                }

            }
        }

        @Parcelize
        data class PropertiesPreliminary(
            @Json(name = "ty_le_sai_lech") val ty_le_sai_lech: Int? = null,
            @Json(name = "ty_le_sai_lech_don_gia") val ty_le_sai_lech_don_gia: Int? = null,
            @Json(name = "ty_le_sai_lech_gia_tri") val ty_le_sai_lech_gia_tri: Int? = null
        ) : Parcelable

        @Parcelize
        data class Photo(
            @Json(name = "photo_id") val id: String,
            @Json(name = "photo_url") val url: String,
            @Json(name = "photo_title") val title: String,
            @Json(name = "photo_size") val size: Double,
            @Json(name = "photo_tag") val tag: String?,
            @Json(name = "photo_extension") val extension: String,
            @Json(name = "content_type") val contentType: String,
            @Json(name = "photo_date") val photoDate: Long
        ) : Parcelable

        @Parcelize
        data class PropertyType(
            @Json(name = "id") val id: Int? = null,
            @Json(name = "value") val value: String? = null,
            @Json(name = "key") val key: String? = null
        ) : Parcelable

        @Parcelize
        data class ShipperAddress(
            val shipper_address: String? = null,
            val shipper_address_1: String? = null,
            val shipper_address_2: String? = null,
            val shipper_city_code: String? = null,
            val shipper_city_name: String? = null,
            val shipper_district_code: String? = null,
            val shipper_district_name: String? = null,
            val shipper_location: @RawValue Any? = null,
            val shipper_no: @RawValue Any? = null,
            val shipper_street: String? = null,
            val shipper_ward_code: String? = null,
            val shipper_ward_name: String? = null
        ) : Parcelable

        @Parcelize
        data class RecordPrice(
            @Json(name = "evaluation_fee") val evaluationFee: Long? = null,
            @Json(name = "hoa_hong") val hoa_hong: Long? = null,
            @Json(name = "khoang_cach") val khoang_cach: String? = null,
            @Json(name = "advance_fee") val advanceFee: Long? = null,
            @Json(name = "remain_fee") val remainFee: Long? = null,
            @Json(name = "total_price") val totalPrice: Long? = null,
            @Json(name = "total_valuation_fee") val totalValuationFee: Long? = null,
            @Json(name = "extra_fee") val extraFee: Long? = null,
            @Json(name = "extra_fee_min") val extraFeeMin: Long? = null,
            @Json(name = "extra_by_manual") val extraByManual: Boolean? = null,
            @Json(name = "translate_fee") val translateFee: Long? = null,
            @Json(name = "voucher_fee") val voucherFee: Long? = null,
            @Json(name = "transaction_status") val transactionStatus: String? = null,
            @Json(name = "payment_method") val paymentMethod: PaymentMethod? = null,
            @Json(name = "allow_charge_full") val allowChargeFull: Boolean? = null,
            @Json(name = "contract_fee") val contractFee: ContractFee? = null
        ) : Parcelable {

            @Parcelize
            data class ContractFee(
                @Json(name = "income") val income: Long? = null,
                @Json(name = "evaluation_fee") val evaluationFee: Long? = null,
                @Json(name = "advance_fee") val advanceFee: Long? = null,
                @Json(name = "remain_fee") val remainFee: Long? = null,
                @Json(name = "transaction_status") val transactionStatus: String? = null,
                @Json(name = "payment_method") val paymentMethod: PaymentMethod? = null,
                @Json(name = "allow_charge_full") val allowChargeFull: Boolean? = null,
            ) : Parcelable

        }

        @Parcelize
        data class PaymentMethod(
            @Json(name = "key") val key: String,
            @Json(name = "title") val title: String,
        ) : Parcelable

        @Parcelize
        data class RecordStatus(
            @Json(name = "status") val status: Int,
            @Json(name = "index") val index: Int,
            @Json(name = "step") val step: Float,
            @Json(name = "status_title") val title: String,
            @Json(name = "color") val color: String? = null,
        ) : Parcelable

        @Parcelize
        data class ExecutionTime(
            @Json(name = "commit") val commit: Long? = null, // timing
            @Json(name = "perform") val perform: Long? = null, // remain_timing
            @Json(name = "started_date") val startedDate: Long? = null,
            @Json(name = "ended_date") val endedDate: Long? = null,
            @Json(name = "finished_date") val finishedDate: Long? = null,
            @Json(name = "time_in_works") val timeInWorks: List<TimeInWork>
        ) : Parcelable

        @Parcelize
        data class ExecutionTimes(
            @Json(name = "authority") val authority: String,
            @Json(name = "commit") val commit: Long,
            @Json(name = "perform") val perform: Long,
            @Json(name = "started_date") val startedDate: Long,
            @Json(name = "ended_date") val endedDate: Long,
            @Json(name = "finished_date") val finishedDate: Long? = null,
            @Json(name = "time_in_works") val timeInWorks: List<TimeInWork>
        ) : Parcelable

        @Parcelize
        data class TimeInWork(
            @Json(name = "start") val start: Long? = null, @Json(name = "end") val end: Long? = null
        ) : Parcelable

        @Parcelize
        data class RecordActive(
            @Json(name = "id") val id: Int,
            @Json(name = "title") val title: String,
            @Json(name = "color") val color: String? = null
        ) : Parcelable

        @Parcelize
        data class RecordOwner(
            @Json(name = "role") val role: List<Role>? = null,
            @Json(name = "phone_no") val phoneNo: String? = null,
            @Json(name = "email") val email: String? = null,
            @Json(name = "user_id") val userId: String? = null,
            @Json(name = "name") val name: String? = null,
            @Json(name = "avatar") val avatar: String? = null,
            @Json(name = "card_number") val card_number: String? = null,
            @Json(name = "current_action") val current_action: List<String?>? = null,
            @Json(name = "user_type") val user_type: String? = null,

            // Bank
            @Json(name = "work_place") val workplace: String? = null,
            @Json(name = "icon") val icon: String? = null,
            @Json(name = "group") val group: Group? = null,
            @Json(name = "active") val active: Boolean? = null
        ) : Parcelable {
            @Parcelize
            data class Role(
                @Json(name = "role") val role: String,
                @Json(name = "title") val title: String? = null,
                @Json(name = "priority") val priority: Int? = null
            ) : Parcelable

            @Parcelize
            data class Group(
                @Json(name = "group_id") val id: String,
                @Json(name = "group_name") val name: String,
                @Json(name = "setting") val setting: Setting? = null,
                @Json(name = "template_code") val code: String? = null
            ) : Parcelable

            @Parcelize
            data class Setting(
                val ap_dung_ctxd: Boolean? = null,
                val ap_dung_nn: Boolean? = null,
                val ap_dung_vpqh: Boolean? = null,
                val tinh_thanh_khoan: Boolean? = null
            ) : Parcelable

        }

        @Parcelize
        data class Legals(
            val tep_phap_ly_dinh_kem: TepPhapLyDinhKem? = null
        ) : Parcelable

        @Parcelize
        data class TepPhapLyDinhKem(
            val content: @RawValue Any? = null,
            val files: List<File?>? = null,
            val title: String? = null
        ) : Parcelable

        @Parcelize
        data class File(
            val content: @RawValue Any? = null,
            val content_type: String? = null,
            val photo_date: Long? = null,
            val photo_extension: String? = null,
            val photo_id: String? = null,
            val photo_size: Int? = null,
            val photo_tag: String? = null,
            val photo_thumbnail: @RawValue Any? = null,
            val photo_title: String? = null,
            val photo_url: String? = null,
            val priority: Int? = null
        ) : Parcelable

    }
}
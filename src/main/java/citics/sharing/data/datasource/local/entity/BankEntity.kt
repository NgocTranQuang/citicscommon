package citics.sharing.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bank")
data class BankEntity(
    @PrimaryKey @ColumnInfo(name = "bankId") val bank_id: Int = 0,
    @ColumnInfo(name = "bankName") val bank_name: String = ""
)
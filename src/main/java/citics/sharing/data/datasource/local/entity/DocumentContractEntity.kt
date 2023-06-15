package citics.sharing.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ChinhQT on 12/10/2022.
 */
@Entity(tableName = "DocumentContract")
data class DocumentContractEntity(
    @PrimaryKey @ColumnInfo(name = "key") val key: String = "",
    @ColumnInfo(name = "title") val title: String = ""
)
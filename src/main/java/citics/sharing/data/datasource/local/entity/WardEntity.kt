package citics.sharing.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ChinhQT on 12/10/2022.
 */
@Entity(tableName = "Ward")
data class WardEntity(
    @PrimaryKey @ColumnInfo(name = "code") val code: String = "",
    @ColumnInfo(name = "name") val name: String = ""
)
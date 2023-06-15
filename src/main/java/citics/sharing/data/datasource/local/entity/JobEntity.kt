package citics.sharing.data.datasource.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 12/10/2022.
 */
@Parcelize
@Entity(tableName = "Jobs")
data class JobEntity(
    @PrimaryKey @ColumnInfo(name = "key") val key: String = "",
    @ColumnInfo(name = "name") val name: String = ""
) : Parcelable
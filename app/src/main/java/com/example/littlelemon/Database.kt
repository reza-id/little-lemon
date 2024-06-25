package com.example.littlelemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase


@Entity
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val desc: String,
    val category: String,
    val image: String,
)

fun MenuItemNetwork.toMenuItemEntity() = MenuItemEntity(
    id = id,
    title = title,
    price = price,
    desc = description,
    image = image,
    category = category
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItemEntity")
    fun getAll(): LiveData<List<MenuItemEntity>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemEntity)

    @Query("SELECT (SELECT COUNT(*) FROM  MenuItemEntity) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "littlelemon.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
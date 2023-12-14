package com.example.tripjournal_project


import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity
data class JourneyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    // Add other properties as needed
)

@Database(entities = [JourneyEntity::class], version = 1)
abstract class JourneyDatabase : RoomDatabase() {
    abstract fun journeyDao(): JourneyDao

    companion object {
        @Volatile
        private var INSTANCE: JourneyDatabase? = null

        fun getDatabase(context: Context): JourneyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JourneyDatabase::class.java,
                    "journey_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@Dao
interface JourneyDao {
    @Query("SELECT * FROM JourneyEntity")
    fun getAllJourneys(): LiveData<List<JourneyEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJourney(journey: JourneyEntity)
}
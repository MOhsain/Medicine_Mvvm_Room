package com.test.medmvvm.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.room.Room
import com.test.medmvvm.local_db.MedicineDao
import com.test.medmvvm.local_db.RoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context
    }


    @Provides
    internal fun provideSharedPrefs(): SharedPreferences {
        return context.getSharedPreferences("ma_account-prefs", Context.MODE_PRIVATE)
    }

    @Provides
    internal fun getResources(): Resources {
        return context.resources
    }


    @Singleton
    @Provides
    fun provideRoomDB(context: Context): RoomDB {
        return Room.databaseBuilder(
            context,
            RoomDB::class.java, "TestdB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideContactsDao(roomDb: RoomDB): MedicineDao {
        return roomDb.medDao()
    }

}
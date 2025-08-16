package com.example.androidmasterclass.hilt.modules

import android.content.Context
import androidx.room.Room
import com.example.androidmasterclass.modules.room_db.db.CustomRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


    @Provides
    @Singleton
    fun providesOneToOneDatabase(@ApplicationContext context: Context): CustomRoomDatabase {
        return Room.databaseBuilder(context, CustomRoomDatabase::class.java,"DB").build()
    }
}
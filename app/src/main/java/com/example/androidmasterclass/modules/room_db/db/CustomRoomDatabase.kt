package com.example.androidmasterclass.modules.room_db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidmasterclass.modules.room_db.many_to_many.db.ManyToManyDao
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudent
import com.example.androidmasterclass.modules.room_db.many_to_many.models.DataStudentCourseCross
import com.example.androidmasterclass.modules.room_db.one_to_one.db.OneToOneDao
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataUser

@Database(entities = [DataUser::class, DataAddress::class, DataCourse::class, DataStudent::class, DataStudentCourseCross::class], version = 1)
abstract class CustomRoomDatabase : RoomDatabase(){
    abstract fun getOneToOneDao() : OneToOneDao
    abstract fun getManyToManyDao() : ManyToManyDao
}
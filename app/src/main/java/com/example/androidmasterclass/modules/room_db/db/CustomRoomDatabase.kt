package com.example.androidmasterclass.modules.room_db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidmasterclass.modules.room_db.many_to_many.data.dao.ManyToManyDao
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.DataCourse
import com.example.androidmasterclass.modules.room_db.many_to_many.domain.models.DataStudent
import com.example.androidmasterclass.modules.room_db.many_to_many.data.models.DataStudentCourseCross
import com.example.androidmasterclass.modules.room_db.one_to_one.data.dao.OneToOneDao
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataUser

@Database(entities = [DataUser::class, DataAddress::class, DataCourse::class, DataStudent::class, DataStudentCourseCross::class], version = 1)
abstract class CustomRoomDatabase : RoomDatabase(){
    abstract fun getOneToOneDao() : OneToOneDao
    abstract fun getManyToManyDao() : ManyToManyDao
}
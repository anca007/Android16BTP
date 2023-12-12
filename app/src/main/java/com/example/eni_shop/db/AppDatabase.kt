package com.example.eni_shop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.utils.DateRoomConverter

@Database(entities = [Article::class], version = 1)
@TypeConverters(DateRoomConverter::class)
abstract class AppDatabase : RoomDatabase (){

    //liste des daos
    abstract fun articleDao() : ArticleDAO
    companion object{

        private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase{

            var instance = INSTANCE

            if(instance == null){

                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "eni-shop"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
            }

            return instance
        }
    }



}
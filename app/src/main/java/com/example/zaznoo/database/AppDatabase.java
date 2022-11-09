package com.example.zaznoo.database;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.zaznoo.models.ActivityStatistics;
import com.example.zaznoo.models.User;
import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooTable;
import com.example.zaznoo.models.ZaznooUpdate;


@Database(entities = {ZaznooActivity.class, ZaznooTable.class, ActivityStatistics.class, ZaznooUpdate.class, User.class},version = 8)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase create(Context context){

        return Room.databaseBuilder(context,AppDatabase.class,"Local_DB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }


    public abstract ZaznooActivityDao zaznooActivityDao();
    public abstract ActivityStatisticsDao activityStatisticsDao();
    public abstract ZaznooTableDao zaznooTableDao();
    public abstract ZaznooUpdateDao zaznooUpdateDao();
    public abstract ZaznooUserDao zaznooUserDao();

}

package com.example.zaznoo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.zaznoo.models.ActivityStatistics;
import com.example.zaznoo.models.User;
import com.example.zaznoo.models.ZaznooUserResponse;

import java.util.List;
@Dao
public interface ZaznooUserDao {
    @Query("Select * From User")
    LiveData<List<User>> getZaznooUsers();


    @Query("Select * From User Where user = :user")
    LiveData<User> getZaznooUserByUser(String user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertUser(User...users);

    @Delete()
    void deleteZaznooUserResponse(User users);

    @Query("Delete from User where user = :user")
    void  deleteZaznooUserResponseById(String user);


}

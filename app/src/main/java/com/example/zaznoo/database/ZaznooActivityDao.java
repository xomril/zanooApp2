package com.example.zaznoo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooActivityPojo;

import java.util.List;

@Dao
public interface ZaznooActivityDao {
    @Query("Select * From zaznooActivity")
    LiveData<List<ZaznooActivity>> getZaznooActivitys();

    @Query("Select * From zaznooActivity Where id = :id")
    LiveData<ZaznooActivity> getZaznooActivityById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertZaznooActivity(ZaznooActivity...game);

    @Delete()
    void deleteZaznooActivity(ZaznooActivity game);

    @Query("Delete from zaznooActivity where id = :id")
    void  deleteZaznooActivityById(int id);

    @Query("Delete from zaznooActivity")
    void  deleteAllZaznooActivitys();

}

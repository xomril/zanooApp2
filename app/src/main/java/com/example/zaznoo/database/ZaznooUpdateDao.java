package com.example.zaznoo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooUpdate;

import java.util.List;
@Dao
public interface ZaznooUpdateDao {

    @Query("Select * From ZaznooUpdate")
    LiveData<List<ZaznooUpdate>> getZaznooUpdates();

    @Query("Select * From ZaznooUpdate Where id = :id")
    LiveData<ZaznooUpdate> getZaznooUpdateById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertUpdates(ZaznooUpdate[] zaznooUpdate);

    @Delete()
    void deleteZaznooUpdate(ZaznooUpdate updatepdate);

    @Query("Delete from ZaznooUpdate where id = :id")
    void  deleteZaznooUpdateById(int id);

    @Query("Delete from ZaznooUpdate")
    void  deleteAllZZaznooUpdate();


}

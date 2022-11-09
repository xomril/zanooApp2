package com.example.zaznoo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooTable;

import java.util.List;

@Dao
public interface ZaznooTableDao {
    @Query("Select * From zaznooTable")
    LiveData<List<ZaznooTable>> getZaznooTable();

    @Query("Select * From zaznooTable Where place = :id")
    LiveData<ZaznooTable> getZaznooTableById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertZaznooTable(ZaznooTable...table);

//    @Delete()
//    void deleteZaznooTable(ZaznooTable table);

    @Query("Delete from zaznooTable where place = :id")
    void  deleteZaznooTableById(int id);

    @Query("Delete from zaznooTable")
    void  deletezaznooTable();
}

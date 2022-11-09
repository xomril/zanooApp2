package com.example.zaznoo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.zaznoo.models.ActivityStatistics;
import com.example.zaznoo.models.ZaznooActivity;

import java.util.List;
@Dao
public interface ActivityStatisticsDao {

    @Query("Select * From activityStatistics")
    LiveData<List<ActivityStatistics>> getActivitysStatistics();

    @Query("Select * From activityStatistics Where id = :id")
    LiveData<ActivityStatistics> getActivityStatisticsById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertActivityStatistics(ActivityStatistics...statistics);

    @Delete()
    void deleteActivityStatistics(ActivityStatistics statistics);

    @Query("Delete from activityStatistics where id = :id")
    void  deleteActivityStatisticsById(int id);


}

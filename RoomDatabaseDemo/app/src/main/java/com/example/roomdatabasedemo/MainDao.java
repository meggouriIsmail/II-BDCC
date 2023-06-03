package com.example.roomdatabasedemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    // Insert query
    @Insert(onConflict = REPLACE)
    void insert(Country country);
    // Delete query

    @Delete
    void delete(Country country);

    @Delete
    void reset(List<Country> mainData);
    //to do


    // Update query
    @Query("UPDATE Country SET Name= :sName, Population= :sPop, Capital= :sCap where ID=:sID")
    void update(int sID, String sName, int sPop, String sCap);

    // Get all data query
    @Query("SELECT * FROM Country")
    List<Country> getAll();


}

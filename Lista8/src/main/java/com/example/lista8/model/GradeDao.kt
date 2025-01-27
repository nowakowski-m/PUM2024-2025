package com.example.lista8.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GradeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(grade: Grade)

    @Update
    fun update(grade: Grade)

    @Delete
    fun delete(grade: Grade)

    @Query("SELECT * FROM grades ORDER BY id ASC")
    fun getAllGrades(): LiveData<List<Grade>>
}

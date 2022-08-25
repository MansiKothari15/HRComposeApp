package com.app.hrcomposeapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmployeeDao {

    @Insert
    fun addEmployee(employee: Employee)

    @Query("SELECT * FROM employees WHERE employeeName = :name")
    fun findEmployee(name: String): List<Employee>

    @Query("DELETE FROM employees WHERE employeeName = :name")
    fun deleteEmployee(name: String)

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): LiveData<List<Employee>>
}
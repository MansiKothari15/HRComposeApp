package com.app.hrcomposeapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmployeeDao {

    @Insert
    fun addEmployee(employee: Employee)

    @Query("SELECT * FROM employees WHERE employeeId = :empId")
    fun findEmployeeById(empId: String): Employee

    @Query("DELETE FROM employees WHERE employeeName = :name")
    fun deleteEmployee(name: String)

    @Query("SELECT * FROM employees")
    suspend fun getAllEmployees(): List<Employee>
}
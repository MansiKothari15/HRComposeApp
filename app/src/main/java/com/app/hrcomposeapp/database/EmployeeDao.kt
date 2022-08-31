package com.app.hrcomposeapp.database

import androidx.room.*

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmployee(employee: Employee)

    @Query("SELECT * FROM employees WHERE employeeId = :empId")
    fun findEmployeeById(empId: String): Employee

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): List<Employee>

    @Update
    suspend fun updateEmployeeDetails(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)
}
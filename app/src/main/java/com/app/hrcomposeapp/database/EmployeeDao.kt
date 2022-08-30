package com.app.hrcomposeapp.database

import androidx.room.*

@Dao
interface EmployeeDao {

    @Insert
    fun addEmployee(employee: Employee)

    @Query("SELECT * FROM employees WHERE employeeId = :empId")
    fun findEmployeeById(empId: String): Employee

    @Query("SELECT * FROM employees")
    suspend fun getAllEmployees(): List<Employee>

    @Update
    fun updateEmployeeDetails(employee: Employee)

    @Delete
    fun deleteEmployee(employee: Employee)
}
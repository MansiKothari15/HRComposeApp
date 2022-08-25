package com.app.hrcomposeapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.database.EmployeeRoomDatabase
import com.app.hrcomposeapp.repository.EmployeeRepository

class HomeViewModel(application: Application) : ViewModel() {

    val allEmployees: LiveData<List<Employee>>
    private val repository: EmployeeRepository
    val searchResults: MutableLiveData<List<Employee>>

    init {
        val employeeDb = EmployeeRoomDatabase.getInstance(application)
        val employeeDao = employeeDb.employeeDao()
        repository = EmployeeRepository(employeeDao)

        allEmployees = repository.allEmployees
        searchResults = repository.searchResults
    }

    fun addEmployee(employee: Employee) {
        repository.addEmployee(employee)
    }

    fun findEmployee(name: String) {
        repository.findEmployee(name)
    }

    fun deleteEmployee(name: String) {
        repository.deleteEmployee(name)
    }
}
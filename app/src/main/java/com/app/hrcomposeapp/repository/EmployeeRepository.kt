package com.app.hrcomposeapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.database.EmployeeDao
import kotlinx.coroutines.*

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    val allEmployees: LiveData<List<Employee>> = employeeDao.getAllEmployees()
    val searchResults = MutableLiveData<List<Employee>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addEmployee(newEmployee: Employee) {
        coroutineScope.launch(Dispatchers.IO) {
            employeeDao.addEmployee(newEmployee)
        }
    }

    fun deleteEmployee(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            employeeDao.deleteEmployee(name)
        }
    }

    fun findEmployee(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Employee>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async employeeDao.findEmployee(name)
        }

}
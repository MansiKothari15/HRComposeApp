package com.app.hrcomposeapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val employeeRepository: EmployeeRepository) :
    ViewModel() {

    fun getAllEmployees(){
        employeeRepository.getAllEmployees()
    }
    fun addEmployee(employee: Employee) {
        employeeRepository.addEmployee(employee)
        getAllEmployees()
    }

    fun findEmployeeById(empId: String) {
        employeeRepository.findEmployeeById(empId)
    }

    fun deleteEmployee(name: String) {
        employeeRepository.deleteEmployee(name)
    }

    val employeeList: LiveData<List<Employee>> = employeeRepository.allEmployees

    val foundEmployee: LiveData<Employee> = employeeRepository.foundEmployee

}
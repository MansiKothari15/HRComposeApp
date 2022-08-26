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

    init {

    }

    fun getAllEmployees(){
        employeeRepository.getAllEmployees()
    }

    //private var allEmployees = MutableLiveData<List<Employee>>()
//        private var searchResults: MutableLiveData<List<Employee>>
//
//        init {
//
//            allEmployees = employeeRepository.allEmployees
//            searchResults = employeeRepository.searchResults
//        }`

    fun addEmployee(employee: Employee) {
        employeeRepository.addEmployee(employee)
        getAllEmployees()
    }

//    fun findEmployee(name: String) {
//        employeeRepository.findEmployee(name)
//    }

    fun deleteEmployee(name: String) {
        employeeRepository.deleteEmployee(name)
    }

    val employeeList: LiveData<List<Employee>> = employeeRepository.allEmployees

}
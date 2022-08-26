package com.app.hrcomposeapp.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee (

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "employeeId")
    var employeeId: Int = 0,

    @ColumnInfo(name = "employeeName")
    var employeeName: String = "",

    @ColumnInfo(name = "designation")
    var employeeDesignation: String = "",

    @ColumnInfo(name = "experience")
    var empExperience: Int = -1,

    @ColumnInfo(name = "email")
    var empEmail: String = "",

    @ColumnInfo(name = "phoneNo")
    var empPhoneNo: Long = -1
)
package com.app.hrcomposeapp.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
class Employee {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "employeeId")
    var id: Int = 0

    @ColumnInfo(name = "employeeName")
    var employeeName: String = ""

    @ColumnInfo(name = "designation")
    var employeeDesignation: String = ""

    @ColumnInfo(name = "experience")
    var experience: Int = -1

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "phoneNo")
    var PhoneNo: Long = -1

    constructor() {}

    constructor(
        id: Int,
        employeeName: String,
        designation: String,
        experience: Int,
        Email: String,
        PhoneNo: Long
    ) {
        this.id = id
        this.employeeName = employeeName
        this.employeeDesignation = designation
        this.experience = experience
        this.email = Email
        this.PhoneNo = PhoneNo
    }
}
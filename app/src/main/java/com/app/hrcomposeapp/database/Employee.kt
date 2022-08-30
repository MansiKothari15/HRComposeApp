package com.app.hrcomposeapp.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "employees")
data class Employee (

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "employeeId")
    var employeeId: Long,

    @ColumnInfo(name = "employeeName")
    var employeeName: String,

    @ColumnInfo(name = "designation")
    var employeeDesignation: String,

    @ColumnInfo(name = "experience")
    var empExperience: Int,

    @ColumnInfo(name = "email")
    var empEmail: String,

    @ColumnInfo(name = "phoneNo")
    var empPhoneNo: Long
): Parcelable
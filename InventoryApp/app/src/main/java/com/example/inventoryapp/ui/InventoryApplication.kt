package com.example.inventoryapp.ui

import android.app.Application
import com.example.inventoryapp.data.AppDatabase

class InventoryApplication : Application(){
    //This will create the database (the physical database on the disk) on the first access.
    //use this database instance later when creating a ViewModel instance.
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
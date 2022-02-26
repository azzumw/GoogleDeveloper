package com.example.forageapp

import android.app.Application
import com.example.forageapp.data.ForageableDatabase

class BaseApplication : Application() {
    val database:ForageableDatabase by lazy { ForageableDatabase.getDatabase(this) }
}
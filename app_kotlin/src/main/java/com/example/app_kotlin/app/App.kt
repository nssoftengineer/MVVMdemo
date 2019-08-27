package com.example.app_kotlin.app

import android.app.Application
import com.example.app_kotlin.db.AppDatabase
import com.example.app_kotlin.utils.AppExecutors
import com.example.app_kotlin.utils.Repository

class App: Application() {
    internal lateinit var appExecutors: AppExecutors
    override fun onCreate() {
        super.onCreate()
        appExecutors = AppExecutors()

    }

    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this, appExecutors)
    }

    fun getRepository(): Repository {
        return Repository.getInstance(getDatabase())
    }
}
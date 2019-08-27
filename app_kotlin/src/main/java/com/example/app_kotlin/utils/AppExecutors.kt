package com.example.app_kotlin.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors {
    private lateinit var mDiskIO: Executor

    private lateinit var mNetworkIO: Executor

    private lateinit var mMainThread: Executor

    private fun AppExecutors(diskIO: Executor, networkIO: Executor, mainThread: Executor) {
        this.mDiskIO = diskIO
        this.mNetworkIO = networkIO
        this.mMainThread = mainThread
    }

    constructor() {
        AppExecutors(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
                MainThreadExecutor())
    }

    fun diskIO(): Executor {
        return mDiskIO
    }

    fun networkIO(): Executor {
        return mNetworkIO
    }

    fun mainThread(): Executor {
        return mMainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}
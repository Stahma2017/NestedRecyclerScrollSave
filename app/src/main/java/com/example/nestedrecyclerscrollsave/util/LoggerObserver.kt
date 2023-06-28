package com.example.nestedrecyclerscrollsave.util

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class LoggerObserver: DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(TAG, "${owner.javaClass.simpleName}, onCreate(), hashCode = ${owner.hashCode()}")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(TAG, "${owner.javaClass.simpleName}, onDestroy(), hashCode = ${owner.hashCode()}")
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(TAG, "${owner.javaClass.simpleName}, onStart(), hashCode = ${owner.hashCode()}")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(TAG, "${owner.javaClass.simpleName}, onResume(), hashCode = ${owner.hashCode()}")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(TAG, "${owner.javaClass.simpleName}, onPause(), hashCode = ${owner.hashCode()}")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(TAG, "${owner.javaClass.simpleName}, onStop(), hashCode = ${owner.hashCode()}")
    }

    companion object {
        const val TAG = "lifecycle_event"
    }
}
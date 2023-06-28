package com.example.nestedrecyclerscrollsave.ui

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        Log.d("TEST14","${this.javaClass.simpleName}, init, hashCode = ${this.hashCode()}")
    }

    override fun onSaveInstanceState(): Parcelable? {
        Log.d("TEST14","${this.javaClass.simpleName}, onSaveInstanceState(), hashCode = ${this.hashCode()}")
        return super.onSaveInstanceState()
    }


    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
        Log.d("TEST14","${this.javaClass.simpleName}, onRestoreInstanceState(), hashCode = ${this.hashCode()}")
    }
}
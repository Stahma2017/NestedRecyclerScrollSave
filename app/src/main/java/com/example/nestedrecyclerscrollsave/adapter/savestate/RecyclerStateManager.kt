package com.example.nestedrecyclerscrollsave.adapter.savestate

import android.os.Parcelable
import java.lang.ref.WeakReference

class RecyclerStateManager {

    private val stateOwners: HashMap<String, WeakReference<LayoutManagerOwner>> = hashMapOf()
    private val states: HashMap<String, Parcelable?> = hashMapOf()

    operator fun set(key: String, value: LayoutManagerOwner) {
        stateOwners[key] = WeakReference(value)
    }

    fun saveViewStates() {
        for (entry in stateOwners) {
            val lmOwner = entry.value.get() ?: continue
            val state = lmOwner.layoutManager?.onSaveInstanceState() ?: continue
            states[entry.key] = state
        }
    }

    fun restoreViewStates() {
        for (entry in states) {
            val lmOwner = stateOwners[entry.key]?.get() ?: continue
            val state = entry.value ?: continue
            lmOwner.layoutManager?.onRestoreInstanceState(state)
        }
    }
}
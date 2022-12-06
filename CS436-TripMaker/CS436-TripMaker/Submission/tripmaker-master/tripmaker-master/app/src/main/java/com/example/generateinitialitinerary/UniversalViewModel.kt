package com.example.generateinitialitinerary

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UniversalViewModel(val state: SavedStateHandle) : ViewModel(), DefaultLifecycleObserver {

    private lateinit var _taskResult : ArrayList<String>
    val taskResult : ArrayList<String>
            get() = _taskResult

    private lateinit var _taskTitle : String
    val taskTitle : String
            get() = _taskTitle

    fun setTaskResult(taskResult : ArrayList<String>) {
        _taskResult = taskResult
    }

    fun addTaskResult(extra : String) {
        _taskResult.add(extra)
    }

    fun setTaskTitle(title : String) {
        _taskTitle = title
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.e("test_tag", "onStop called in view model")
        state["result"] = _taskResult
        state["title"] = _taskTitle
        state["stopped"] = true
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.e("test_tag", "onResume called in view model")
        super.onResume(owner)
        if (state.get("stopped") as Boolean? == true) {
            Log.e("test_tag", "onResume success")
            Log.e("test_tag", "made in to interior in view model")
            _taskResult = state["result"]!!
            _taskTitle = state["title"]!!
        }
    }

    fun resetValues() {
        _taskResult = state["result"]!!
        _taskTitle = state["title"]!!
    }



    fun bindToActivityLifeCycle(activity: AlbumsActivity) {
        activity.lifecycle.addObserver(this)
    }


}
package com.example.gitapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gitapp.model.GrRepository
import com.example.gitapp.model.entity.GR
import kotlinx.coroutines.Dispatchers

class GitRepositoryViewModel(private val application: Application) : ViewModel() {
    private val grRepository: GrRepository = GrRepository(application)
    internal var allGrs: LiveData<List<GR>> = MutableLiveData()

    init {
            allGrs = liveData(Dispatchers.IO) { emitSource(grRepository.getGrs()) }
    }

}
package com.example.homeciti.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.homeciti.core.Resource
import com.example.homeciti.remote.quickAccessService.QuickAccessServiceRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class QuickAccessViewModel(private val repo: QuickAccessServiceRepo) : ViewModel() {
    fun getQuickAccessServiceList() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getQuickAccessServiceList()))
        } catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}
class QuickAccessViewModelFactory(private val repo: QuickAccessServiceRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuickAccessViewModel(repo) as T
    }

}
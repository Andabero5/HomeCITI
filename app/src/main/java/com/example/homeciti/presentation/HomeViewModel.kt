package com.example.homeciti.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.homeciti.core.Resource
import com.example.homeciti.remote.homeService.HomeServiceRepo
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class HomeViewModel(private val repo: HomeServiceRepo) : ViewModel() {
    fun getHomeServiceList() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getHomeServiceList()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }


}
class HomeViewModelFactory (private val repo: HomeServiceRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repo) as T
    }
}
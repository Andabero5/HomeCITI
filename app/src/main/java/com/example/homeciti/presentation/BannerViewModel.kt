package com.example.homeciti.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.homeciti.core.Resource
import com.example.homeciti.remote.bannerService.BannerServiceRepo
import kotlinx.coroutines.Dispatchers

class BannerViewModel(private val repo: BannerServiceRepo) : ViewModel() {
    fun getBannerServiceList() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getBannerServiceList()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}
class BannerViewModelFactory(private val repo:BannerServiceRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BannerViewModel(repo) as T
    }

}
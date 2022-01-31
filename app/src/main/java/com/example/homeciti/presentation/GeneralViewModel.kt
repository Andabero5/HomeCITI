package com.example.homeciti.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.homeciti.core.Resource
import com.example.homeciti.remote.generalService.GeneralServiceRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class GeneralViewModel(private val repo: GeneralServiceRepo): ViewModel() {
    fun getGeneralServiceList() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getGeneralServiceList()))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}
class GeneralViewModelFactory(private val repo: GeneralServiceRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GeneralViewModel(repo) as T
    }

}
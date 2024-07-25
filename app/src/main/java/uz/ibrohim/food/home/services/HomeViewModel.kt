package uz.ibrohim.food.home.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.ibrohim.food.retrofit.NetworkHelper
import uz.ibrohim.food.retrofit.Resource
import java.lang.Exception

class HomeViewModel(apiServices: HomeServices, private val networkHelper: NetworkHelper) : ViewModel() {

    private val moveRepository = HomeRepository(apiServices)
    val flow = MutableStateFlow<Resource>(Resource.Loading)

    fun getList(a: String): StateFlow<Resource> {
        viewModelScope.launch {
            try {
                if (networkHelper.isNetworkConnected()) {
                    val response = moveRepository.getLogin(a)
                    if (response.isSuccessful && response.body() != null) {
                        flow.emit(Resource.Success(response.body()!!))
                    } else {
                        flow.emit(Resource.Error("Sever Error !"))
                    }
                } else {
                    flow.emit(Resource.Error("Network no connection !"))
                }
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        }
        return flow
    }
}
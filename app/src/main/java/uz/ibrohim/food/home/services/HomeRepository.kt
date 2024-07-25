package uz.ibrohim.food.home.services

class HomeRepository(private val apiServices: HomeServices) {

    suspend fun getLogin(a: String) = apiServices.getList(a)
}
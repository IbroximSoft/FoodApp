package uz.ibrohim.food.home.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import uz.ibrohim.food.home.response.HomeResponse

interface HomeServices {

    @GET("api/json/v1/1/search.php")
    suspend fun getList(@Query("f") a: String): Response<HomeResponse>
}
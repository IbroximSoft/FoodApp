package uz.ibrohim.food.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.ibrohim.food.home.services.HomeServices

object ApiClient {

    private const val base_url = "https://www.themealdb.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build()
    }

//    Response code test
//    var client: OkHttpClient = OkHttpClient.Builder()
//        .addInterceptor(ChuckerInterceptor(App.instance))
//        .build()

    val apiServices: HomeServices = getRetrofit().create(HomeServices::class.java)
}
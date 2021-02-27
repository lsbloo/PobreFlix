package app.student.movieapp.core.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createHttpLogginInterceptor())
        .build()



    private fun createHttpLogginInterceptor(): OkHttpClient{

        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(httpInterceptor).build()
    }

    fun apiServiceMovie()  = retrofit.create(ApiService::class.java)

    companion object {
        const val BASE_URL="https://api.themoviedb.org/3/"
    }
}
package ru.cse.app.common

import android.app.Application
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.cse.app.interfaces.ApiProvider


class DeliveryAgentApplication : Application() {

    lateinit var api: ApiProvider

    override fun onCreate() {
        super.onCreate()

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.cse.ru")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(ApiProvider::class.java)
    }
}

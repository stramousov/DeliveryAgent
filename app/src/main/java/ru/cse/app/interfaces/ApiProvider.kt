package ru.cse.app.interfaces

import io.reactivex.Observable
import retrofit2.http.POST
import ru.cse.app.models.Safekeep

interface ApiProvider {
    @POST("service/hs/deliveryagent/getsafekeeplist")
    fun Safekeeper(): Observable<List<Safekeep>>
}
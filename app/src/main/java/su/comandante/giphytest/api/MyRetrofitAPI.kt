package su.comandante.giphytest.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface MyRetrofitAPI {

    @GET
    fun getGifs(@Url url: String): Observable<ResponseBody?>?
}
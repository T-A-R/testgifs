package su.comandante.giphytest

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import su.comandante.giphytest.api.MyRetrofitAPI
import java.util.concurrent.TimeUnit

class CoreApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.Default.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitAPI = retrofit.create(MyRetrofitAPI::class.java)

    }

    companion object {
        @JvmStatic
        var retrofitAPI: MyRetrofitAPI? = null
            private set
    }
}
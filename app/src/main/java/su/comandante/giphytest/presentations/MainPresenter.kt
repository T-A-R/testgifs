package su.comandante.giphytest.presentations

import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import su.comandante.giphytest.Constants
import su.comandante.giphytest.CoreApp
import su.comandante.giphytest.models.ResponseModel
import su.comandante.giphytest.models.ServerResponse
import su.comandante.giphytest.utils.StringUtils
import java.util.concurrent.TimeUnit

class MainPresenter() {

    private var disposableSearchIndex: Disposable? = null
    private lateinit var adapter: GifsAdapter

    fun findIndex(editText: EditText?, listView: RecyclerView, adapter: GifsAdapter) {
        this.adapter = adapter

        editText?.let {
            disposableSearchIndex = RxTextView.textChangeEvents(editText)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .filter { changes -> StringUtils.isNotNullOrEmpty(changes.text().toString()) }
                .map { listener -> listener.text().toString() }
                .flatMap {
                    return@flatMap CoreApp.retrofitAPI?.getGifs(Constants.Default.API_URL + it + Constants.Default.URL_PARAMS)
                        ?.onErrorResumeNext(Observable.empty())
                }
                .map { body -> getStringIndex(body) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getSearchObserver(listView))
        } ?: run {
            disposableSearchIndex = Observable.just("giphy")
                .flatMap {
                    return@flatMap CoreApp.retrofitAPI?.getGifs(Constants.Default.API_URL + it + Constants.Default.URL_PARAMS)
                        ?.onErrorResumeNext(Observable.empty())
                }
                .map { body -> getStringIndex(body) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getSearchObserver(listView))
        }
    }

    private fun getSearchObserver(listView: RecyclerView): DisposableObserver<List<ResponseModel>>? {
        return object : DisposableObserver<List<ResponseModel>>() {
            override fun onComplete() {
                Log.d("TAG", "onComplete: ")
            }

            override fun onError(e: Throwable) {
                Log.d("TAG", "onError: ${e.message}")
            }

            override fun onNext(indexList: List<ResponseModel>) {
                adapter.setList(indexList)
                adapter.notifyDataSetChanged()
            }
        }


    }

    private fun getStringIndex(data: ResponseBody?): List<ResponseModel> {
        data?.let {
            val json: String = data.string()
            val serverAnswer1: ServerResponse?

            try {
                serverAnswer1 = GsonBuilder().create().fromJson(json, ServerResponse::class.java)
            } catch (pE: Exception) {

                pE.printStackTrace()
                return listOf()
            }

            var indexList: List<ResponseModel>
            serverAnswer1?.let {
                indexList = serverAnswer1.data
                return indexList
            }
        }
        return listOf()
    }

    fun stopSearchIndex() {
        disposableSearchIndex?.dispose()
    }
}
package su.comandante.giphytest.presentations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import su.comandante.giphytest.R

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: GifsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        initRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stopSearchIndex()
    }

    private fun initRecyclerView() {
        runOnUiThread {
            val recyclerView: RecyclerView = findViewById(R.id.gifs_recyclerview)
            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter = GifsAdapter(listOf(), this)
            recyclerView.adapter = adapter
            presenter.findIndex(null, recyclerView, adapter)
            presenter.findIndex(findViewById(R.id.edit), recyclerView, adapter)
        }
    }
}
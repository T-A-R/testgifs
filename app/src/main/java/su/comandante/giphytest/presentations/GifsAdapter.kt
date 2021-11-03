package su.comandante.giphytest.presentations

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import su.comandante.giphytest.R
import su.comandante.giphytest.models.ResponseModel

class GifsAdapter(private var gifsList: List<ResponseModel>, val context : Context) : RecyclerView.Adapter<GifsAdapter.StockHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.holder_gif, parent, false)
        return StockHolder(v)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.bindItems(gifsList[position], position)
    }

    override fun getItemCount(): Int {
        return gifsList.size
    }

    fun setList(list: List<ResponseModel>) {
        this.gifsList = list
    }

    inner class StockHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindItems(gif: ResponseModel, position: Int) {
            val imageCont = itemView.findViewById(R.id.imageView) as ImageView

            Glide.with(context).load(gif.images.preview_gif.url).into(imageCont);
        }

    }

}
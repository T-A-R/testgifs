package su.comandante.giphytest.models

import com.google.gson.annotations.SerializedName

class GifModel(
    @SerializedName("height")
    val height : Int,

    @SerializedName("width")
    val width : Int,

    @SerializedName("url")
    val url: String) {
}
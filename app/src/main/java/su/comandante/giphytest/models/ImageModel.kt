package su.comandante.giphytest.models

import com.google.gson.annotations.SerializedName

class ImageModel(
    @SerializedName("preview_gif")
    val preview_gif: GifModel
) {
}
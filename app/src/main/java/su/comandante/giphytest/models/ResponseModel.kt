package su.comandante.giphytest.models

import com.google.gson.annotations.SerializedName

class ResponseModel(
    @SerializedName("images")
    val images: ImageModel)
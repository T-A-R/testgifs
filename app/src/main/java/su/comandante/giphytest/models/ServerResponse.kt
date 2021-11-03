package su.comandante.giphytest.models

import com.google.gson.annotations.SerializedName

class ServerResponse(@SerializedName("data")
                     val data: List<ResponseModel>)
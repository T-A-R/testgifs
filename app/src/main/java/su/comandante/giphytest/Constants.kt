package su.comandante.giphytest

class Constants {

    interface Default {
        companion object {
            const val BASE_URL = "https://api.giphy.com/v1/gifs/"
            const val API_URL = "https://api.giphy.com/v1/gifs/search?q="
            const val API_KEY = "quAJGTIsOfyG50cz5Z5aaMaUm5HIAEjA"
            const val URL_PARAMS = "&rating=q&api_key=$API_KEY&lang=ru"
        }
    }
}
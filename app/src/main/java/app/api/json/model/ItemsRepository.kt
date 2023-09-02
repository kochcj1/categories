package app.api.json.model

import app.api.json.BuildConfig
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsRepository {

    private val client = OkHttpClient()

    suspend fun fetch(): List<Item> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(BuildConfig.API_URL)
                .build()

            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    response.body?.string()?.let { json ->
                        val items = JsonParser.parseString(json).asJsonArray.map { jsonElement ->
                            val jsonObject = jsonElement.asJsonObject
                            Item(
                                jsonObject[BuildConfig.TITLE_FIELD].asString,
                                jsonObject[BuildConfig.URL_FIELD].asString,
                                jsonObject[BuildConfig.THUMBNAIL_URL_FIELD].asString
                            )
                        }
                        return@withContext items
                    }
                }
                return@withContext arrayListOf()
            }
        }
    }
}

package app.api.categories.model.items

import app.api.categories.configuration.getItems
import app.api.categories.configuration.getRequest
import okhttp3.OkHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsRepository(private val category: String) {

    private val client = OkHttpClient()

    suspend fun fetch(): Items {
        return withContext(Dispatchers.IO) {
            val request = getRequest(category)
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    response.body?.string()?.let { body ->
                        return@withContext getItems(body)
                    }
                }
                return@withContext arrayListOf()
            }
        }
    }
}

package app.api.json.model

interface ItemsRepository {
    suspend fun fetch(): Items
}
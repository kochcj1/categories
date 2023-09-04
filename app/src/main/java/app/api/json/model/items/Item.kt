package app.api.json.model.items

data class Item(
    val title: String,
    val url: String,
    val photoUrl: String
)

typealias Items = ArrayList<Item>
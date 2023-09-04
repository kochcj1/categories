package app.api.categories.model.items

data class Item(
    val title: String,
    val url: String,
    val photoUrl: String
)

typealias Items = ArrayList<Item>
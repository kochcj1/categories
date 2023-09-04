package app.api.json.model

import androidx.annotation.DrawableRes
import app.api.json.configuration.CategoryType

data class Category(
    val type: CategoryType,
    @DrawableRes val image: Int
) {
    val title = type.name
}

typealias Categories = ArrayList<Category>

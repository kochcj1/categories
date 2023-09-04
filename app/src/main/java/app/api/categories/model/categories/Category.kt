package app.api.categories.model.categories

import androidx.annotation.DrawableRes
import app.api.categories.configuration.CategoryType

data class Category(
    val type: CategoryType,
    @DrawableRes val image: Int
) {
    val title = type.name
}

typealias Categories = ArrayList<Category>
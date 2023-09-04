package app.api.json.model.categories

import app.api.json.R
import app.api.json.configuration.CategoryType
import app.api.json.model.categories.Category

class CategoryFactory {

    companion object {
        fun from(categoryType: CategoryType): Category {
            val image = when(categoryType) {
                CategoryType.American -> R.drawable.united_states
                CategoryType.Chinese -> R.drawable.china
                CategoryType.Greek -> R.drawable.greece
                CategoryType.Indian -> R.drawable.india
                CategoryType.Italian -> R.drawable.italy
                CategoryType.Japanese -> R.drawable.japan
                CategoryType.Mexican -> R.drawable.mexico
                CategoryType.Thai -> R.drawable.thailand
            }
            return Category(categoryType, image)
        }
    }

}
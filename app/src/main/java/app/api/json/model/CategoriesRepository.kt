package app.api.json.model

import android.content.Context
import androidx.core.content.edit
import app.api.json.R

class CategoriesRepository(private val context: Context) {

    companion object {
        const val SHARED_PREFS_ID = "categories_prefs"
        const val SHARED_PREFS_CATEGORIES_KEY = "categories_key"
    }

    fun add(category: Category) {
        val categoriesSharedPref = getCategoriesSharedPreference()
        getSharedPreferences().edit {
            putString(
                SHARED_PREFS_CATEGORIES_KEY,
                if (categoriesSharedPref.isEmpty()) category.title else (categoriesSharedPref + ",${category.title}")
            )
        }
    }

    fun fetch(): Categories {
        val categoriesSharedPref = getCategoriesSharedPreference()
        val categoryIds = if (categoriesSharedPref.isEmpty()) emptyList() else categoriesSharedPref.split(",")
        return ArrayList(categoryIds.map { CategoryFactory.from(CategoryType.valueOf(it)) }.toList())
    }

    private fun getSharedPreferences() =
        context.getSharedPreferences(SHARED_PREFS_ID, Context.MODE_PRIVATE)

    private fun getCategoriesSharedPreference() =
        getSharedPreferences().getString(SHARED_PREFS_CATEGORIES_KEY, "") ?: ""

}